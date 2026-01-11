import { useState } from "react";
import { Box, TextField, Button, Typography, Input } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { candidateService } from "../../services/candidateService";

export default function CreateCandidate() {
  const [form, setForm] = useState({
    name: "",
    skills: "",
    experience: "",
    score: "",
    resume: null as File | null
  });
  const navigate = useNavigate();

  const handleSubmit = async () => {
    const formData = new FormData();
    formData.append("name", form.name);
    formData.append("skills", JSON.stringify(form.skills.split(",").map(s => s.trim())));
    formData.append("experience", form.experience);
    formData.append("score", form.score);


      await candidateService.create(formData);
    if (form.resume) {
      await candidateService.uploadResume(form.name, form.resume);
    }
    navigate("/recruiter/candidates");
  };

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (file && (file.type === "application/pdf" || file.type === "application/msword" || file.type === "application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
      setForm({ ...form, resume: file });
    } else {
      alert("Please select a valid PDF or DOC file");
    }
  };

  return (
    <Box>
      <Typography variant="h4" gutterBottom>Create Candidate</Typography>
      <TextField
        fullWidth
        label="Name"
        margin="normal"
        value={form.name}
        onChange={(e) => setForm({ ...form, name: e.target.value })}
      />
      <TextField
        fullWidth
        label="Skills (comma separated)"
        margin="normal"
        value={form.skills}
        onChange={(e) => setForm({ ...form, skills: e.target.value })}
      />
      <TextField
        fullWidth
        label="Experience"
        type="number"
        margin="normal"
        value={form.experience}
        onChange={(e) => setForm({ ...form, experience: e.target.value })}
      />
      <TextField
        fullWidth
        label="Score"
        type="number"
        margin="normal"
        value={form.score}
        onChange={(e) => setForm({ ...form, score: e.target.value })}
      />
      <Box sx={{ mt: 2, mb: 2 }}>
        <Typography variant="body1" gutterBottom>Resume (PDF or DOC)</Typography>
        <Input
          type="file"
          inputProps={{ accept: ".pdf,.doc,.docx" }}
          onChange={handleFileChange}
          fullWidth
        />
        {form.resume && (
          <Typography variant="body2" sx={{ mt: 1 }}>
            Selected: {form.resume.name}
          </Typography>
        )}
      </Box>
      <Button variant="contained" onClick={handleSubmit} disabled={!form.name || !form.resume}>
        Create
      </Button>
    </Box>
  );
}