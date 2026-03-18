import {useEffect,useState} from "react";
import Layout from "../components/Layout";

import {
getStudentFees,
makePayment,
getStudentPayments,
getReceipt,
createOrder
} from "../services/feeService";

function MyFees(){

const studentId = localStorage.getItem("studentId");

const [fees,setFees] = useState([]);
const [payments,setPayments] = useState([]);
const [receipt,setReceipt] = useState(null);

const [payment,setPayment] = useState({
amount:"",
paymentMethod:"UPI",
transactionId:""
});

useEffect(()=>{

loadFees();
loadPayments();

},[]);

const loadFees = async ()=>{

const res = await getStudentFees(studentId);
setFees(res.data);

};

const loadPayments = async ()=>{

const res = await getStudentPayments(studentId);
setPayments(res.data);

};

const handlePay = async (feeId)=>{

await makePayment(feeId,payment);

alert("Payment successful");

loadFees();
loadPayments();

};

const handleReceipt = async (paymentId)=>{

const res = await getReceipt(paymentId);

setReceipt(res.data);

};

const handlePayment = async (feeId, amount) => {
  try {

    // 1. Create order
    const res = await createOrder(feeId);
    const order = typeof res.data === "string"
      ? JSON.parse(res.data)
      : res.data;
    console.log("Order response:", order);

    // 2. Open Razorpay
    const options = {
      key: process.env.REACT_APP_RAZORPAY_KEY,
      amount: order.amount,
      currency: "INR",
      name: "Hostel Management",
      description: "Fee Payment",
      order_id: order.id,

      handler: async function () {

        // 3. AFTER SUCCESS  call  existing API
        await makePayment(feeId, {
          amount: amount,
          paymentMethod: "ONLINE",
          transactionId: order.id
        });

        alert("Payment Successful ");

        loadFees();
        loadPayments();
      }
    };

    const rzp = new window.Razorpay(options);
    rzp.open();

  } catch (e) {
    console.error(e);
    alert("Payment Failed");
  }
};

return(

<Layout>
    <div className="page-container">

<h2>My Fees</h2>

<table className="table">

<thead>

<tr>
<th>Fee ID</th>
<th>Type</th>
<th>Amount</th>
<th>Status</th>
<th>Pay</th>
</tr>

</thead>

<tbody>

{fees.map(f=>(

<tr key={f.feeId}>

<td>{f.feeId}</td>
<td>{f.feeType}</td>
<td>{f.amount}</td>
<td>{f.status}</td>



{/* <input className="input-field"
placeholder="Amount"
onChange={(e)=>setPayment({...payment,amount:e.target.value})}
/>

<select className="input-field"
onChange={(e)=>setPayment({...payment,paymentMethod:e.target.value})}
>

<option>UPI</option>
<option>CARD</option>
<option>CASH</option>

</select>

<input className="input-field"
placeholder="Transaction ID"
onChange={(e)=>setPayment({...payment,transactionId:e.target.value})}
/> */}

{/* <button className="actio-btn checkout-btn" onClick={()=>handlePay(f.feeId)}>
Pay
</button> */}

<td>
  {f.status === "PAID" ? (
    <span style={{color:"green", fontWeight:"bold"}}>PAID</span>
  ) : (
    <button
      className="action-btn checkout-btn"
      onClick={() => handlePayment(f.feeId, f.amount)}
    >
      Pay Now
    </button>
  )}
</td>



</tr>

))}

</tbody>

</table>



<h3>Payment History</h3>

<table className="table">

<thead>

<tr>
<th>ID</th>
<th>Amount</th>
<th>Method</th>
<th>Receipt</th>
</tr>

</thead>

<tbody>

{payments.map(p=>(

<tr key={p.paymentId}>

<td>{p.paymentId}</td>
<td>{p.amount}</td>
<td>{p.paymentMethod}</td>

<td>

<button className="action-btn view-btn" onClick={()=>handleReceipt(p.paymentId)}>
View Receipt
</button>

</td>

</tr>

))}

</tbody>

</table>

{receipt && (


<div className="form-card"> 
<h4>Receipt</h4>

<p>Payment ID: {receipt.paymentId}</p>
<p>Amount: {receipt.amount}</p>
<p>Method: {receipt.paymentMethod}</p>

</div>

)}
</div>
</Layout>

);

}

export default MyFees;