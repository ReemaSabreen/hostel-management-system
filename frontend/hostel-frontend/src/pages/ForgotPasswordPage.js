import { useState } from "react";
import { resetPassword } from "../services/authService";
import { useNavigate } from "react-router-dom";

function ForgotPasswordPage(){

const navigate = useNavigate();

const [email,setEmail] = useState("");
const [newPassword,setNewPassword] = useState("");

const handleReset = async () => {

  try{

    await resetPassword(email,newPassword);

    alert("Password updated successfully");

    navigate("/");

  }
  catch(err){
    alert("Failed to reset password");
  }

};

return(

<div className="auth-container">
  <div className="auth-card">

<h3 className="auth-title">Reset Password</h3>

<input
className="input-field"
placeholder="Enter Email"
onChange={(e)=>setEmail(e.target.value)}
/>

<input
className="input-field"
type="password"
placeholder="New Password"
onChange={(e)=>setNewPassword(e.target.value)}
/>

<button
className="primary-btn"
onClick={handleReset}
>
Reset Password
</button>
<div className="auth-links">
<p>
<a href="/">Back to Login</a>
</p>
</div>

</div>
</div>

);

}

export default ForgotPasswordPage;