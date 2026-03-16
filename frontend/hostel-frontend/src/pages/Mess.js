import { useState } from "react";
import Layout from "../components/Layout";
import {
createMenu,
getMenu,
markAttendance,
calculateBill,
generateMessFee
} from "../services/messService";

function Mess(){

const [menuDate,setMenuDate] = useState("");
const [menu,setMenu] = useState([]);

const [menuForm,setMenuForm] = useState({
date:"",
mealType:"BREAKFAST",
menuItems:""
});

const [attendance,setAttendance] = useState({
student:{studentId:""},
date:"",
breakfast:false,
lunch:false,
dinner:false
});

const [bill,setBill] = useState(null);
const [studentId,setStudentId] = useState("");

const handleMenuChange = (e)=>{
setMenuForm({...menuForm,[e.target.name]:e.target.value});
};

const handleCreateMenu = async ()=>{
await createMenu(menuForm);
alert("Menu created");
};

const handleViewMenu = async ()=>{
const res = await getMenu(menuDate);
setMenu(res.data);
};

const handleAttendance = async ()=>{
await markAttendance(attendance);
alert("Attendance marked");
};

const handleBill = async ()=>{
const res = await calculateBill(studentId);
setBill(res.data);
};

const handleGenerateFee = async ()=>{
await generateMessFee(studentId);
alert("Mess fee generated");
};

return(

<Layout>
    <div className="page-container">

<h2>Mess Management</h2>
<div className="form-card">
<h4>Create Menu</h4>

<input className="input-field"
name="date"
type="date"
onChange={handleMenuChange}
/>

<select className="input-field"
name="mealType"
onChange={handleMenuChange}
>

<option>BREAKFAST</option>
<option>LUNCH</option>
<option>DINNER</option>

</select>

<input className="input-field"
name="menuItems"
placeholder="Menu items"
onChange={handleMenuChange}
/>

<button className="primary-btn" onClick={handleCreateMenu}>
Create Menu
</button>

</div>
<div className="form-card">
<h4>View Menu</h4>

<input className="input-field"
type="date"
onChange={(e)=>setMenuDate(e.target.value)}
/>

<button className="primary-btn" onClick={handleViewMenu}>
View Menu
</button>

<ul>
{menu.map(m=>(
<li key={m.menuId}>
{m.mealType} : {m.menuItems}
</li>
))}
</ul>

</div>
<div className="form-card">

<h4>Mark Attendance</h4>

<input className="input-field"
placeholder="Student ID"
onChange={(e)=>setAttendance({...attendance,
student:{studentId:e.target.value}})}
/>

<input className="input-field"
type="date"
onChange={(e)=>setAttendance({...attendance,date:e.target.value})}
/>

<label>
<input type="checkbox"
onChange={(e)=>setAttendance({...attendance,breakfast:e.target.checked})}/>
Breakfast
</label>

<label>
<input type="checkbox"
onChange={(e)=>setAttendance({...attendance,lunch:e.target.checked})}/>
Lunch
</label>

<label >
<input type="checkbox"
onChange={(e)=>setAttendance({...attendance,dinner:e.target.checked})}/>
Dinner
</label>

<br/><br/>

<button className="primary-btn" onClick={handleAttendance}>
Mark Attendance
</button>

</div>
<div className="form-card">

<h4>Mess Bill</h4>

<input className="input-field"
placeholder="Student ID"
onChange={(e)=>setStudentId(e.target.value)}
/>

<button className="primary-btn" onClick={handleBill}>
Calculate Bill
</button>

{bill && <p>Bill Amount: ₹{bill}</p>}
<br/><br/>
<button className="action-btn checkout-btn" onClick={handleGenerateFee}>
Generate Mess Fee
</button>
</div>
</div>
</Layout>

);

}

export default Mess;