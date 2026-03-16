import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import ForgotPasswordPage from "./pages/ForgotPasswordPage";
import ProtectedRoute from "./components/ProtectedRoute";
import Dashboard from "./pages/Dashboard";
import Students from "./pages/Students";
import StudentProfile from "./pages/StudentProfile";
import Rooms from "./pages/Rooms";
import Fees from "./pages/Fees";
import MyRoom from "./pages/MyRoom";
import Mess from "./pages/Mess";
import MyFees from "./pages/MyFees";
import Complaints from "./pages/Complaints";
import ComplaintsAdmin from "./pages/ComplaintsAdmin";


function App() {

  return (
    <BrowserRouter>

      <Routes>

        <Route path="/" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/forgot-password" element={<ForgotPasswordPage />} />

        <Route path="/dashboard" element={
<ProtectedRoute>
<Dashboard/>
</ProtectedRoute>
}/>

<Route path="/students" element={<Students/>}/>
<Route path="/rooms" element={<Rooms/>}/>
<Route path="/fees" element={<Fees/>}/>
<Route path="/mess" element={<Mess/>}/>

<Route path="/profile" element={<StudentProfile/>}/>
<Route path="/my-room" element={<MyRoom/>}/>
<Route path="/my-fees" element={<MyFees/>}/>
<Route path="/complaints" element={<Complaints />} />
<Route path="/admin/complaints" element={<ComplaintsAdmin />} />
      </Routes>

    </BrowserRouter>
  );
}

export default App;