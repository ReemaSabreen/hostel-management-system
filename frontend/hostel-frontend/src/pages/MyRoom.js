import { useEffect, useState } from "react";
import Layout from "../components/Layout";
import { getCurrentAllocation } from "../services/roomService";

function MyRoom(){

const [allocation,setAllocation] = useState(null);
const [loading,setLoading] = useState(true);

const studentId = localStorage.getItem("studentId");

useEffect(()=>{
loadCurrentRoom();
},[]);

const loadCurrentRoom = async ()=>{

try{

const res = await getCurrentAllocation(studentId);

setAllocation(res.data);

}catch(error){

// no active room
setAllocation(null);

}finally{

setLoading(false);

}

};

return(

<Layout>

<div className="page-container">

<h2>My Room</h2>

{loading ? (

<p>Loading...</p>

) : allocation ? (

<table className="table">

<thead>

<tr>
<th>Room</th>
<th>Type</th>
<th>Floor</th>
<th>Status</th>
<th>Allocated On</th>
</tr>

</thead>

<tbody>

<tr>

<td>{allocation.room.roomNumber}</td>
<td>{allocation.room.type}</td>
<td>{allocation.room.floor}</td>
<td>{allocation.status}</td>
<td>{allocation.allocationDate}</td>

</tr>

</tbody>

</table>

) : (

<p>No active room allocation</p>

)}

</div>

</Layout>

);

}

export default MyRoom;