import { useParams } from "react-router-dom";
import { Typography, Box } from "@mui/material";

export default function CandidateEvaluation() {
  const { id } = useParams();

  return (
    <Box>
      <Typography variant="h4" gutterBottom>Evaluate Candidate {id}</Typography>
      <Typography>Evaluation form here.</Typography>
    </Box>
  );
}