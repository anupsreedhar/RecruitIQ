import {
  Box,
  Button,
  Paper,
  TextField,
  Typography,
  Alert,
} from "@mui/material";
import { useState } from "react";
import { authService } from "./authService";
import { useNavigate } from "react-router-dom";

export default function Login() {
  console.log("[LOGIN] component rendered");

  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);

  const useMock = import.meta.env.VITE_USE_MOCK === 'true';

  const handleLogin = async () => {
    console.log("[LOGIN] handleLogin clicked");

    setError(null);
    setLoading(true);

    try {
      const res = await authService.login(email, password);

      console.log("[LOGIN] success, token:", res.token);

      sessionStorage.setItem("accessToken", res.token);

      // 🔁 role-based redirect
      const payload = JSON.parse(atob(res.token.split(".")[1]));
      console.log("[LOGIN] decoded payload:", payload);

      if (payload.role === "AGENCY_ADMIN") {
        navigate("/admin/dashboard");
      } else if (payload.role === "RECRUITER") {
        navigate("/recruiter/dashboard");
      } else if (payload.role === "VENDOR_USER") {
        navigate("/vendor/dashboard");
      }
    } catch (err: unknown) {
      console.error("[LOGIN] failed", err);
      setError((err as Error)?.message || "Login failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Box
      display="flex"
      height="100vh"
      alignItems="center"
      justifyContent="center"
    >
      <Paper sx={{ p: 4, width: 360 }}>
        <Typography variant="h5" gutterBottom>
          RecruitIQ Login
        </Typography>

        {error && <Alert severity="error">{error}</Alert>}

        <TextField
          fullWidth
          label="Email"
          margin="normal"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <TextField
          fullWidth
          label="Password"
          type="password"
          margin="normal"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <Button
          fullWidth
          variant="contained"
          sx={{ mt: 2 }}
          onClick={handleLogin}
          disabled={loading}
        >
          {loading ? "Signing in..." : "Login"}
        </Button>

        {/* MOCK HELP */}
        {!useMock ? (
          <Box mt={2} fontSize={12} color="text.secondary">
            <div>Admin: admin@recruitiq.com / admin123</div>
            <div>Recruiter: recruiter@recruitiq.com / Secret123!</div>
            <div>Vendor: vendoruser@recruitiq.com/ VendorPass1!</div>
          </Box>
        ):(
          <Box mt={2} fontSize={12} color="text.secondary">
            <div>Admin: admin@recruitiq.com / admin123</div>
            <div>Recruiter: recruiter@recruitiq.com / recruiter123</div>
            <div>Vendor: vendoruser@recruitiq.com / vendor123</div>
          </Box>
        )}
      </Paper>
    </Box>
  );
}
