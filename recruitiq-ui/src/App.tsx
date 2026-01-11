import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login from "./auth/Login";
import AppLayout from "./layouts/AppLayout";
import ProtectedRoute from "./routes/ProtectedRoute";

/* DASHBOARDS */
import RecruiterDashboard from "./pages/recruiter/Dashboard";
import AdminDashboard from "./pages/admin/Dashboard";

/* RECRUITER */
import CandidateList from "./pages/recruiter/Candidates";
import CreateCandidate from "./pages/recruiter/CreateCandidate";
import CandidateEvaluation from "./pages/recruiter/Evaluation";
import InterviewRequests from "./pages/recruiter/InterviewRequests";

/* ADMIN */
import Users from "./pages/admin/Users";
import Vendors from "./pages/admin/Vendors";

/* VENDOR */
import VendorDashboard from "./pages/vendor/Dashboard";
import AssignedCandidates from "./pages/vendor/AssignedCandidates";
import PostRequirements from "./pages/vendor/PostRequirements";

/* COMMON */
import Profile from "./pages/profile/Profile";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* ================= PUBLIC ================= */}
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="/login" element={<Login />} />

        {/* ================= AGENCY ADMIN ================= */}
        <Route
          element={
            <ProtectedRoute allowedRoles={["AGENCY_ADMIN"]}>
              <AppLayout />
            </ProtectedRoute>
          }
        >
          <Route path="/admin/dashboard" element={<AdminDashboard />} />
          <Route path="/admin/users" element={<Users />} />
          <Route path="/admin/vendors" element={<Vendors />} />
          <Route path="/profile" element={<Profile />} />
        </Route>

        {/* ================= RECRUITER ================= */}
        <Route
          element={
            <ProtectedRoute allowedRoles={["RECRUITER"]}>
              <AppLayout />
            </ProtectedRoute>
          }
        >
          <Route path="/recruiter/dashboard" element={<RecruiterDashboard />} />
          <Route path="/recruiter/candidates" element={<CandidateList />} />
          <Route path="/recruiter/candidates/create" element={<CreateCandidate />} />
          <Route path="/recruiter/evaluate/:id" element={<CandidateEvaluation />} />
          <Route path="/recruiter/interviews" element={<InterviewRequests />} />
          <Route path="/profile" element={<Profile />} />
        </Route>

        {/* ================= VENDOR ================= */}
        <Route
          element={
            <ProtectedRoute allowedRoles={["VENDOR_USER"]}>
              <AppLayout />
            </ProtectedRoute>
          }
        >
          <Route path="/vendor/dashboard" element={<VendorDashboard />} />
          <Route path="/vendor/candidates" element={<AssignedCandidates />} />
          <Route path="/vendor/positions" element={<PostRequirements />} />
          <Route path="/profile" element={<Profile />} />
        </Route>

        {/* ================= FALLBACK ================= */}
        <Route path="*" element={<Navigate to="/login" replace />} />
      </Routes>
    </BrowserRouter>
  );
}
