import { useEffect, useState } from "react";
import Layout from "../components/Layout";

import {
getStudents,
createStudent,
updateStudent,
deleteStudent,
checkoutStudent,
} from "../services/studentService";

import { getStudentUsersWithoutProfile } from "../services/authService";

function Students(){

const [students,setStudents] = useState([]);

const [usersWithoutProfile, setUsersWithoutProfile] = useState([]);
const [editingId,setEditingId] = useState(null);

const [form,setForm] = useState({
userId: "",
enrollmentNo:"",
firstName:"",
lastName:"",
dateOfBirth:"",
gender:"",
phone:"",
emergencyContact:"",
course:"",
year:"",
photoUrl:"",
address:""

});

useEffect(()=>{
loadStudents();
loadUsersWithoutProfile();
},[]);

const loadStudents = async ()=>{

const res = await getStudents();

setStudents(res.data);

};
const loadUsersWithoutProfile = async () => {
    try {
      const res = await getStudentUsersWithoutProfile();
      setUsersWithoutProfile(res.data);
    } catch (error) {
      console.error(error);
    }
  };

const handleChange = (e)=>{

setForm({

...form,
[e.target.name]:e.target.value

});

};

const handleSubmit = async ()=>{

if(editingId){

await updateStudent(editingId,form);

setEditingId(null);

}
else{
    const payload = {
  ...form,
  user: {
    userId: form.userId
  }
};

await createStudent(payload);

}

setForm({
userId:"",
enrollmentNo:"",
firstName:"",
lastName:"",
dateOfBirth:"",
gender:"",
phone:"",
emergencyContact:"",
course:"",
year:"",
photoUrl:"",
address:""

});

loadStudents();
loadUsersWithoutProfile();

};

const handleEdit = (student)=>{

setEditingId(student.studentId);

setForm({
userId: student.user?.userId || "",
enrollmentNo:student.enrollmentNo,
firstName:student.firstName,
lastName:student.lastName,
dateOfBirth:student.dateOfBirth,
gender:student.gender,
phone:student.phone,
emergencyContact:student.emergencyContact,
course:student.course,
year:student.year,
photoUrl:student.photoUrl,
address:student.address

});

};

const handleDelete = async (id)=>{

await deleteStudent(id);

loadStudents();

};

const handleCheckout = async (id)=>{

await checkoutStudent(id);

loadStudents();

};



return(

<Layout>
<div className="page-container">

<h2>Students</h2>
{/* USERS WITHOUT STUDENT PROFILE TABLE */}
<div className="form-card">

        {usersWithoutProfile.length > 0 && (

          <div>

            <h4>Users Waiting For Student Profile</h4>

            <table className="table">

              <thead>
                <tr>
                  <th>User ID</th>
                  <th>Username</th>
                  <th>Email</th>
                  <th>Role</th>
                </tr>
              </thead>

              <tbody>

                {usersWithoutProfile.map((u) => (

                  <tr key={u.userId}>
                    <td>{u.userId}</td>
                    <td>{u.username}</td>
                    <td>{u.email}</td>
                    <td>{u.role}</td>
                  </tr>

                ))}

              </tbody>

            </table>

          </div>

        )}
        </div>

        {/* STUDENT FORM */}
<div className="form-card">

<h5>{editingId ? "Edit Student" : "Add Student"}</h5>
<input className="input-field" name="userId" placeholder="User ID" value={form.userId} onChange={handleChange} />
<input className="input-field" name="enrollmentNo" placeholder="Enrollment No" value={form.enrollmentNo} onChange={handleChange}/> 

<input className="input-field" name="firstName" placeholder="First Name" value={form.firstName} onChange={handleChange}/>

<input className="input-field" name="lastName" placeholder="Last Name" value={form.lastName} onChange={handleChange}/>
<input className="input-field" type ="date" name = "dateOfBirth" placeholder="Date of Birth" value={form.dateOfBirth || ""} onChange={handleChange}/>
<select  className="input-field"name = "gender" value = {form.gender || ""} onChange={handleChange}>
<option value ="">Select Gender</option>
<option value = "MALE">Male</option>
<option value = "FEMALE">Female</option>
<option value = "OTHER">Other</option>
</select>
<input  className="input-field"name="phone" placeholder="Phone" value={form.phone} onChange={handleChange}/>

<input className="input-field" name="emergencyContact" placeholder="Emergency Contact" value={form.emergencyContact} onChange={handleChange}/>

<input className="input-field" name="course" placeholder="Course" value={form.course} onChange={handleChange}/>

<input className="input-field" name="year" placeholder="Year" value={form.year} onChange={handleChange}/>

<input className="input-field"  name="photoUrl" placeholder="Photo URL" value={form.photoUrl} onChange={handleChange}/>

<textarea className="input-field"  name="address" placeholder="Address" value={form.address} onChange={handleChange}/>

<button className="primary-btn" onClick={handleSubmit}>

{editingId ? "Update Student" : "Add Student"}

</button>

</div>

<table className="table">

<thead>

<tr>

<th>ID</th>
<th>Name</th>
<th>DOB</th>
<th>Gender</th>
<th>Enrollment</th>
<th>Course</th>
<th>Year</th>
<th>Phone</th>
<th>Emergency contact</th>
<th>Address</th>
<th>Actions</th>

</tr>

</thead>

<tbody>

{students.map((s)=>(

<tr key={s.studentId}>

<td>{s.studentId}</td>

<td>{s.firstName} {s.lastName}</td>
<td>{s.dateOfBirth}</td>
<td>{s.gender}</td>

<td>{s.enrollmentNo}</td>

<td>{s.course}</td>

<td>{s.year}</td>

<td>{s.phone}</td>
<td>{s.emergencyContact}</td>
<td>{s.address}</td>

<td>

<button
className="action-btn edit-btn"
onClick={()=>handleEdit(s)}
>

Edit

</button>

<button
className="action-btn delete-btn"
onClick={()=>handleDelete(s.studentId)}
>

Delete

</button>

<button
className="action-btn checkout-btn"
onClick={()=>handleCheckout(s.studentId)}
>

Checkout

</button>

</td>

</tr>

))}

</tbody>

</table>
</div>
</Layout>

);

}

export default Students;