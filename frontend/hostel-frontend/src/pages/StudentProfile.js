import { useEffect, useState } from "react";
import Layout from "../components/Layout";
import { getStudentById } from "../services/studentService";

function StudentProfile(){

const [student,setStudent] = useState(null);

const studentId = localStorage.getItem("studentId");

useEffect(()=>{

loadStudent();

},[]);

const loadStudent = async () => {
  try {
    const res = await getStudentById(studentId);
    setStudent(res.data);
  } catch (error) {
    alert("Student not found");
  }
};

if(!student) return <Layout>Loading...</Layout>;

return(

<Layout>
    <div className="page-container">

<h2>My Profile</h2>

<div className="form-card">
<p><b>Student ID:</b> {student.studentId}</p>
<p><b>Name:</b> {student.firstName} {student.lastName}</p>

<p><b>Enrollment:</b> {student.enrollmentNo}</p>

<p><b>Date of Birth:</b> {student.dateOfBirth}</p>

<p><b>Gender:</b> {student.gender}</p>

<p><b>Course:</b> {student.course}</p>

<p><b>Year:</b> {student.year}</p>

<p><b>Phone:</b> {student.phone}</p>

<p><b>Emergency Contact:</b> {student.emergencyContact}</p>

<p><b>Address:</b> {student.address}</p>

</div>
</div>

</Layout>

);

}

export default StudentProfile;