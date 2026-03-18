import React, { useEffect, useState } from "react";
import Layout from "../components/Layout";
import {
  raiseComplaint,
  getStudentComplaints,
} from "../services/complaintService";

function Complaints() {

const [complaints, setComplaints] = useState([]);

const [formData, setFormData] = useState({

category: "",
subject: "",
description: "",
priority: "",
});

const studentId = localStorage.getItem("studentId");

useEffect(() => {
fetchComplaints();
}, []);

const fetchComplaints = async () => {

try{

const res = await getStudentComplaints(studentId);
setComplaints(res.data);

}catch(e){
alert("Unable to load complaints");
}

};

const handleChange = (e) => {

setFormData({
...formData,
[e.target.name]: e.target.value,
});

};

const handleSubmit = async (e) => {

e.preventDefault();

try{

const complaintData = {
...formData,
student: { studentId: studentId },
};

await raiseComplaint(complaintData);

alert("Complaint submitted successfully");

setFormData({
category: "",
subject: "",
description: "",
priority: "",
});

fetchComplaints();

}catch(e){
alert("Failed to submit complaint");
}

};

return(

<Layout>

<div className="page-container">

<h2>Complaints</h2>

{/* Complaint Form */}

<div className="form-card">

<h3>Raise Complaint</h3>

<form onSubmit={handleSubmit}>



<input
className="input-field"
type="text"
name="subject"
placeholder="Subject"
value={formData.subject}
onChange={handleChange}
/>

<textarea
className="input-field"
name="description"
placeholder="Description"
value={formData.description}
onChange={handleChange}
/>

<select
className="input-field"
name="category"
value={formData.category}
onChange={handleChange}
>

<option value="">Select Category</option>
<option value="MAINTENANCE">Maintenance</option>
<option value="MESS">Mess</option>
<option value="CLEANLINESS">Cleanliness</option>
<option value="OTHER">Other</option>

</select>

<select
className="input-field"
name="priority"
value={formData.priority}
onChange={handleChange}
>

<option value="">Select Priority</option>
<option value="LOW">Low</option>
<option value="MEDIUM">Medium</option>
<option value="HIGH">High</option>

</select>

<button className="primary-btn" type="submit">
Submit Complaint
</button>

</form>

</div>

{/* Complaint Table */}

<div className="form-card">

<h3>My Complaints</h3>

<table className="table">

<thead>

<tr>
<th>Ticket</th>
<th>Subject</th>
<th>Status</th>
<th>Priority</th>
</tr>

</thead>

<tbody>

{complaints.map((c) => (

<tr key={c.complaintId}>

<td>{c.ticketNo}</td>
<td>{c.subject}</td>
<td>{c.status}</td>
<td>{c.priority}</td>

</tr>

))}

</tbody>

</table>

</div>

</div>

</Layout>

);

}

export default Complaints;