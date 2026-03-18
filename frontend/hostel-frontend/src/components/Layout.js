import Navbar from "./Navbar";
import NotificationBell from "../pages/NotificationBell";
function Layout({children}){
const role = localStorage.getItem("role");
return(

<div>

<Navbar/>
<div className="nav-right">
  {role === "STUDENT" && <NotificationBell />}
</div>

<div >

{children}

</div>

</div>

);

}

export default Layout;