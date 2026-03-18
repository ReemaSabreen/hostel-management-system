import { useEffect,useState } from "react";
import Layout from "../components/Layout";

import {
getRooms,
createRoom,
updateRoom,
deleteRoom,
allocateRoom,
getAvailableRooms,
getAllAllocations,
getStudentAllocations,
deallocateRoom
} from "../services/roomService";

function Rooms(){

const [rooms,setRooms] = useState([]);
const [availableRooms,setAvailableRooms] = useState([]);
const [allocations,setAllocations] = useState([]);

const [editingId,setEditingId] = useState(null);

const [studentId,setStudentId] = useState("");

const [form,setForm] = useState({
roomNumber:"",
floor:"",
type:"SINGLE",
capacity:"",
status:"AVAILABLE",
rentPerMonth:""
});

const [allocation,setAllocation] = useState({
studentId:"",
roomNumber:""
});

useEffect(()=>{
loadRooms();
},[]);
const [allAllocations,setAllAllocations] = useState([]);

const loadRooms = async ()=>{
try{
const res = await getRooms();
setRooms(res.data);
}catch(e){}
};

const loadAvailableRooms = async ()=>{
try{
const res = await getAvailableRooms();
setAvailableRooms(res.data);
}catch(e){}
};

const loadAllAllocations = async () => {
  try{
    const res = await getAllAllocations();
    setAllAllocations(res.data);
  }catch(e){}
};

const loadAllocations = async ()=>{
if(!studentId) return;

try{
const res = await getStudentAllocations(studentId);
setAllocations(res.data);
}catch(e){}
};


const handleChange = (e)=>{

setForm({
...form,
[e.target.name]:e.target.value
});

};

const handleSubmit = async ()=>{

try{

if(editingId){
await updateRoom(editingId,form);
alert("Room updated");
setEditingId(null);
}else{
await createRoom(form);
alert("Room created");
}

setForm({
roomNumber:"",
floor:"",
type:"SINGLE",
capacity:"",
status:"AVAILABLE",
rentPerMonth:""
});

loadRooms();

}catch(e){}
};

const handleEdit = (room)=>{

setEditingId(room.roomId);
setForm(room);

};

const handleDelete = async (roomId)=>{

try{
await deleteRoom(roomId);
alert("Room deleted");
loadRooms();
}catch(e){}

};

const handleAllocate = async ()=>{

try{

await allocateRoom(allocation.studentId,allocation.roomNumber);

alert("Room Allocated");

loadRooms();

}catch(e){}

};

const handleDeallocate = async (allocationId)=>{

try{

await deallocateRoom(allocationId);

alert("Room Deallocated");

loadAllocations();
loadRooms();
loadAllAllocations();

}catch(e){}

};

return(

<Layout>
    <div className="page-container">

<h2>Rooms</h2>

{/* Create / Edit Room */}

<div className="form-card">

<h3>{editingId ? "Edit Room":"Add Room"}</h3>

<input className="input-field"
placeholder="Room Number"
name="roomNumber"
value={form.roomNumber}
onChange={handleChange}
/>

<input className="input-field"
placeholder="Floor"
name="floor"
value={form.floor}
onChange={handleChange}
/>

<select className="input-field"
name="type"
value={form.type}
onChange={handleChange}
>

<option value="SINGLE">SINGLE</option>
<option value="DOUBLE">DOUBLE</option>
<option value="TRIPLE">TRIPLE</option>
<option value="QUAD">QUAD</option>

</select>



<input className="input-field"
placeholder="Rent Per Month"
name="rentPerMonth"
value={form.rentPerMonth}
onChange={handleChange}
/>

<button className="primary-btn" onClick={handleSubmit}>
{editingId ? "Update Room":"Create Room"}
</button>

</div>



{/* Allocate Room */}
<div className="form-card">
<h3>Allocate Room</h3>

<input className="input-field"
placeholder="Student ID"
onChange={(e)=>setAllocation({...allocation,studentId:e.target.value})}
/>

<select
className="input-field"
onChange={(e)=>setAllocation({...allocation,roomNumber:e.target.value})}
>
<option value="">Select Room</option>

{availableRooms.map(room=>(
  <option key={room.roomId} value={room.roomNumber}>
    {room.roomNumber} (Floor {room.floor})
  </option>
))}

</select>

<button className="primary-btn" onClick={handleAllocate}>
Allocate
</button>

</div>

{/* View Available Rooms */}
<div className="form-card">
<h3>Available Rooms</h3>

<button className="primary-btn" onClick={loadAvailableRooms}>
Load Available Rooms
</button>

<ul>

{availableRooms.map(room=>(
<li key={room.roomId}>
Room {room.roomNumber} | Floor {room.floor} | Type {room.type}
</li>
))}

</ul>

</div>

<div className="form-card">

<h3>All Room Allocations</h3>

<button className="primary-btn" onClick={loadAllAllocations}>
Load All Allocations
</button>

<table className="table">

<thead>
<tr>
<th>Allocation ID</th>
<th>Student ID</th>
<th>Student Name</th>
<th>Room</th>
<th>Status</th>
<th>Allocated On</th>
<th>Deallocated On</th>
</tr>
</thead>

<tbody>

{allAllocations.map(a => (

<tr key={a.allocationId}>

<td>{a.allocationId}</td>
<td>{a.student.studentId}</td>
<td>{a.student.firstName} {a.student.lastName}</td>
<td>{a.room.roomNumber}</td>
<td>{a.status}</td>
<td>{a.allocationDate}</td>
<td>{a.deallocationDate || "-"}</td>

</tr>

))}

</tbody>

</table>

</div>

{/* Student Allocations */}
<div className="form-card">
<h3>Student Allocations</h3>

<input className="input-field"
placeholder="Student ID"
onChange={(e)=>setStudentId(e.target.value)}
/>

<button className="primary-btn" onClick={loadAllocations}>
Load Allocations
</button>

<table className="table">

<thead>

<tr>
<th>Allocation ID</th>
<th>Room</th>
<th>Status</th>
<th>Action</th>
</tr>

</thead>

<tbody>

{allocations.map(a=>(

<tr key={a.allocationId}>

<td>{a.allocationId}</td>
<td>{a.room.roomNumber}</td>
<td>{a.status}</td>

<td>

<button className="action-btn warning-btn"
disabled={a.status === "COMPLETED"}
onClick={()=>handleDeallocate(a.allocationId)}
>
{a.status === "COMPLETED"? "Deallocated":"Deallocate"}
</button>

</td>

</tr>

))}

</tbody>

</table>

</div>

{/* All Rooms Table */}
<div className="form-card">

<h3>All Rooms</h3>

<table className="table">

<thead>

<tr>
<th>ID</th>
<th>Room</th>
<th>Floor</th>
<th>Type</th>
<th>Capacity</th>
<th>Status</th>
<th>Rent</th>
<th>Actions</th>
</tr>

</thead>

<tbody>

{rooms.map(room=>(

<tr key={room.roomId}>

<td>{room.roomId}</td>
<td>{room.roomNumber}</td>
<td>{room.floor}</td>
<td>{room.type}</td>
<td>{room.capacity}</td>
<td>{room.status}</td>
<td>{room.rentPerMonth}</td>

<td>

<button className="action-btn edit-btn" onClick={()=>handleEdit(room)}>
Edit
</button>

<button className="action-btn delete-btn" onClick={()=>handleDelete(room.roomId)}>
Delete
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

export default Rooms;