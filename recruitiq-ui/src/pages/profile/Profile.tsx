import { useState, useEffect } from "react";
import {
  Typography,
  Box,
  TextField,
  Button,
  Paper,
  Alert,
  CircularProgress,
} from "@mui/material";
import { userService, type UserProfile } from "../../services/userService";

export default function Profile() {
  const [profile, setProfile] = useState<UserProfile | null>(null);
  const [editing, setEditing] = useState(false);
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState(false);
  const [formData, setFormData] = useState({ name: "", email: "" });

  useEffect(() => {
    loadProfile();
  }, []);

  const loadProfile = async () => {
    try {
      setLoading(true);
      const userProfile = await userService.getProfile();
      setProfile(userProfile);
      setFormData({ name: userProfile.name, email: userProfile.email });
    } catch {
      setError("Failed to load profile");
    } finally {
      setLoading(false);
    }
  };

  const handleEdit = () => {
    setEditing(true);
    setError(null);
    setSuccess(false);
  };

  const handleCancel = () => {
    setEditing(false);
    if (profile) {
      setFormData({ name: profile.name, email: profile.email });
    }
    setError(null);
  };

  const handleSave = async () => {
    if (!formData.name.trim() || !formData.email.trim()) {
      setError("Name and email are required");
      return;
    }

    try {
      setSaving(true);
      setError(null);
      const updatedProfile = await userService.updateProfile({
        name: formData.name.trim(),
        email: formData.email.trim(),
      });
      setProfile(updatedProfile);
      setEditing(false);
      setSuccess(true);
      setTimeout(() => setSuccess(false), 3000);
    } catch {
      setError("Failed to update profile");
    } finally {
      setSaving(false);
    }
  };

  const handleInputChange = (field: keyof typeof formData) => (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setFormData(prev => ({ ...prev, [field]: event.target.value }));
  };

  if (loading) {
    return (
      <Box display="flex" justifyContent="center" mt={4}>
        <CircularProgress />
      </Box>
    );
  }

  if (!profile) {
    return (
      <Box>
        <Typography variant="h4" gutterBottom>Profile</Typography>
        <Alert severity="error">Failed to load profile data</Alert>
      </Box>
    );
  }

  return (
    <Box>
      <Typography variant="h4" gutterBottom>Profile</Typography>

      {error && (
        <Alert severity="error" sx={{ mb: 2 }}>
          {error}
        </Alert>
      )}

      {success && (
        <Alert severity="success" sx={{ mb: 2 }}>
          Profile updated successfully!
        </Alert>
      )}

      <Paper sx={{ p: 3, maxWidth: 600 }}>
        <Box display="flex" flexDirection="column" gap={3}>
          <TextField
            fullWidth
            label="Name"
            value={formData.name}
            onChange={handleInputChange("name")}
            disabled={!editing}
            variant={editing ? "outlined" : "filled"}
          />

          <TextField
            fullWidth
            label="Email"
            type="email"
            value={formData.email}
            onChange={handleInputChange("email")}
            disabled={!editing}
            variant={editing ? "outlined" : "filled"}
          />

          <TextField
            fullWidth
            label="Role"
            value={profile.role}
            disabled
            variant="filled"
            helperText="Role cannot be changed"
          />

          <Box display="flex" gap={2}>
            {!editing ? (
              <Button variant="contained" onClick={handleEdit}>
                Edit Profile
              </Button>
            ) : (
              <>
                <Button
                  variant="contained"
                  onClick={handleSave}
                  disabled={saving}
                >
                  {saving ? <CircularProgress size={20} /> : "Save Changes"}
                </Button>
                <Button variant="outlined" onClick={handleCancel} disabled={saving}>
                  Cancel
                </Button>
              </>
            )}
          </Box>
        </Box>
      </Paper>
    </Box>
  );
}