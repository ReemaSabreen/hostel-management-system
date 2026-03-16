import React, { useEffect, useState } from "react";
import Layout from "../components/Layout";

import {
getAllComplaints,
updateComplaintStatus,
} from "../services/complaintService";

function ComplaintsAdmin(){

const [complaints,setComplaints] = useState([]);

useEffect(()=>{
fetchComplaints();
},[]);

const fetchComplaints = async ()=>{

try{

const res = await getAllComplaints();
setComplaints(res.data);

}catch(e){
alert("Failed to load complaints");
}

};

const handleStatusUpdate = async (id,status)=>{

try{

await updateComplaintStatus(id,status);

alert("Status updated");

fetchComplaints();

}catch(e){

alert("Failed to update status");

}

};

return(

<Layout>

<div className="page-container">

<h2>Complaint Management</h2>

<div className="form-card">

<table className="table">

<thead>

<tr>
<th>Ticket</th>
<th>Student</th>
<th>Subject</th>
<th>Status</th>
<th>Priority</th>
<th>Action</th>
</tr>

</thead>

<tbody>

{complaints.map(c=>(

<tr key={c.complaintId}>

<td>{c.ticketNo}</td>

<td>{c.student?.studentId}</td>

<td>{c.subject}</td>

<td>{c.status}</td>

<td>{c.priority}</td>

<td>

<button
className="action-btn edit-btn"
onClick={()=>handleStatusUpdate(c.complaintId,"IN_PROGRESS")}
>
In Progress
</button>

<button
className="action-btn checkout-btn"
onClick={()=>handleStatusUpdate(c.complaintId,"RESOLVED")}
>
Resolve
</button>

</td>

</tr>

))}

</tbody>

</table>

</div>

</div>

</Layout>

);

}

export default ComplaintsAdmin;