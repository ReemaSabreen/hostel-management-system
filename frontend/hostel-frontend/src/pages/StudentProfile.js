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
   <div className="profile-card">

  <div className="profile-header">
    <div className="profile-avatar">
      {student.firstName[0]}
    </div>

    <div>
      <h3>{student.firstName} {student.lastName}</h3>
      <p className="profile-sub">{student.course} - Year {student.year}</p>
    </div>
  </div>

  <div className="profile-grid">

    <div className="profile-item">
      <label>Student ID</label>
      <span>{student.studentId}</span>
    </div>

    <div className="profile-item">
      <label>Enrollment</label>
      <span>{student.enrollmentNo}</span>
    </div>

    <div className="profile-item">
      <label>Date of Birth</label>
      <span>{student.dateOfBirth}</span>
    </div>

    <div className="profile-item">
      <label>Gender</label>
      <span>{student.gender}</span>
    </div>

    <div className="profile-item">
      <label>Phone</label>
      <span>{student.phone}</span>
    </div>


    <div className="profile-item">
      <label>Emergency Contact</label>
      <span>{student.emergencyContact}</span>
    </div>

    <div className="profile-item full-width">
      <label>Address</label>
      <span>{student.address}</span>
    </div>

  </div>

</div>
</Layout>

);

}

export default StudentProfile;