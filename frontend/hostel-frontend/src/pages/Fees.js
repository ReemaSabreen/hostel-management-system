import {useState} from "react";
import Layout from "../components/Layout";
import {
createFee,
updateFee,
getFeeById,
getDefaulters,
getAllFees,
getStudentFees
} from "../services/feeService";

function Fees(){

const [fee,setFee] = useState({
studentId:"",
feeType:"ROOM_RENT",
amount:""
});

const [feeId,setFeeId] = useState("");

const [defaulters,setDefaulters] = useState([]);
const [allFees,setAllFees] = useState([]);
const [studentFees,setStudentFees] = useState([]);
const [searchStudentId,setSearchStudentId] = useState("");

const handleChange = (e)=>{

setFee({
...fee,
[e.target.name]:e.target.value
});

};

const handleCreate = async ()=>{

try{

const body = {
student:{studentId:fee.studentId},
feeType:fee.feeType,
amount:fee.amount
};

await createFee(body);

alert("Fee created successfully");

setFee({
studentId:"",
feeType:"ROOM_RENT",
amount:"",
dueDate:""
});

}catch(e){}

};

const handleUpdate = async ()=>{

try{

const body = {
feeType:fee.feeType,
amount:fee.amount
};

await updateFee(feeId,body);

alert("Fee updated successfully");

}catch(e){}

};

const loadAllFees = async ()=>{

try{
const res = await getAllFees();
setAllFees(res.data);
}catch(e){}

};

const loadStudentFees = async ()=>{

try{
const res = await getStudentFees(searchStudentId);
setStudentFees(res.data);
}catch(e){}

};

const handleGetFee = async ()=>{

try{

const res = await getFeeById(feeId);

alert(
`Fee ID: ${res.data.feeId}
Type: ${res.data.feeType}
Amount: ${res.data.amount}
Status: ${res.data.status}`
);

}catch(e){}

};

const handleDefaulters = async ()=>{

try{

const res = await getDefaulters();
setDefaulters(res.data);

}catch(e){}

};

return(

<Layout>
<div className="page-container">
<h2>Fee Management</h2>
<div className="form-card">
<h3>Create Fee</h3>

<input
className="input-field"
placeholder="Student ID"
name="studentId"
value={fee.studentId}
onChange={handleChange}
/>

<select className="input-field"
name="feeType" value={fee.feeType}
onChange={handleChange}
>

<option value="ROOM_RENT">ROOM_RENT</option>
<option value="MESS">MESS</option>
<option value="SECURITY_DEPOSIT">SECURITY_DEPOSIT</option>
<option value="OTHER">OTHER</option>

</select>

<input className="input-field"
placeholder="Amount"
name="amount"
value={fee.amount}
onChange={handleChange}
/>


<button className="primary-btn" onClick={handleCreate}>
Create Fee
</button>
</div>

<div className="form-card">

<h3>Get Student Fees</h3>

<input
className="input-field"
placeholder="Student ID"
onChange={(e)=>setSearchStudentId(e.target.value)}
/>

<button className="primary-btn" onClick={loadStudentFees}>
Load Student Fees
</button>

<table className="table">

<thead>
<tr>
<th>Fee ID</th>
<th>Type</th>
<th>Amount</th>
<th>Status</th>
<th>Due Date</th>
</tr>
</thead>

<tbody>

{studentFees.map(f=>(
<tr key={f.feeId}>

<td>{f.feeId}</td>
<td>{f.feeType}</td>
<td>{f.amount}</td>
<td>{f.status}</td>
<td>{f.dueDate}</td>

</tr>
))}

</tbody>

</table>

</div>

<div className="form-card">

<h3>All Fees</h3>

<button className="primary-btn" onClick={loadAllFees}>
Load All Fees
</button>

<table className="table">

<thead>

<tr>
<th>Fee ID</th>
<th>Student</th>
<th>Type</th>
<th>Amount</th>
<th>Status</th>
<th>Due Date</th>
</tr>

</thead>

<tbody>

{allFees.map(f=>(
<tr key={f.feeId}>

<td>{f.feeId}</td>
<td>{f.student.studentId}</td>
<td>{f.feeType}</td>
<td>{f.amount}</td>
<td>{f.status}</td>
<td>{f.dueDate}</td>

</tr>
))}

</tbody>

</table>

</div>

<div className="form-card">
<input
className="input-field"
placeholder="Fee ID"
onChange={(e)=>setFeeId(e.target.value)}
/>
<div className="action-group">
<button className="action-btn update-btn" onClick={handleUpdate}>
Update Fee
</button>

<button className="action-btn view-btn" onClick={handleGetFee}>
Get Fee
</button>
</div>
</div>
<div className="form-card">
    <h3>Defaulters</h3>

<button className="primary-btn" onClick={handleDefaulters}>
View Defaulters
</button>

<table className="table">

<thead>
<tr>
<th>Student</th>
<th>Fee Type</th>
<th>Amount</th>
</tr>
</thead>

<tbody>

{defaulters.map(f=>(
<tr key={f.feeId}>
<td>{f.student.studentId}</td>
<td>{f.feeType}</td>
<td>{f.amount}</td>
</tr>
))}

</tbody>

</table>

</div>


</div>
</Layout>

);

}

export default Fees;