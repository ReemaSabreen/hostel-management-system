import { useNavigate } from "react-router-dom";

function Navbar(){

const navigate = useNavigate();

const logout = () => {

localStorage.clear();

navigate("/");

};

return(

<nav className="navbar">
<div className="nav-title">
Hostel Management
</div>

<div ></div>

<button className="action-btn delete-btn" onClick={logout}>
Logout
</button>

</nav>

);

}

export default Navbar;