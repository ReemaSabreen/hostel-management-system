import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Layout from "./../components/Layout";

function Dashboard(){

const navigate = useNavigate();
const [role,setRole] = useState("");

useEffect(()=>{

const r = localStorage.getItem("role");
setRole(r);

},[]);

const Card = ({title,path}) => (

<div
className="dashboard-card"
onClick={()=>navigate(path)}
>

<h3>{title}</h3>

</div>

);

return(

<Layout>

<div className="page-container">

<h2>Dashboard</h2>

<div className="dashboard-grid">

{/* ADMIN */}

{role === "ADMIN" && (
<>
<Card title="Students" path="/students"/>
<Card title="Rooms" path="/rooms"/>
<Card title="Fees" path="/fees"/>
<Card title="Complaints" path="/admin/complaints"/>
</>
)}

{/* WARDEN */}

{role === "WARDEN" && (
<>
<Card title="Students" path="/students"/>
<Card title="Rooms" path="/rooms"/>
<Card title="Complaints" path="/admin/complaints"/>
</>
)}

{/* MESS MANAGER */}

{role === "MESS_MANAGER" && (
<>
<Card title="Mess Management" path="/mess"/>

</>
)}

{/* STUDENT */}

{role === "STUDENT" && (
<>
<Card title="My Profile" path="/profile"/>
<Card title="My Room" path="/my-room"/>
<Card title="My Fees" path="/my-fees"/>
<Card title="My Complaints" path="/complaints"/>
</>
)}

</div>
</div>
</Layout>

);

}

export default Dashboard;