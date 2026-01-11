import { useEffect, useState } from "react";
import { DataGrid } from "@mui/x-data-grid";
import type { GridColDef } from "@mui/x-data-grid";
import { Box, Typography, Button, IconButton } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { candidateService } from "../../services/candidateService";
import EditIcon from "@mui/icons-material/Edit";

export default function Candidates() {
  const [candidates, setCandidates] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    candidateService.getAll().then(setCandidates);
  }, []);

  const columns: GridColDef[] = [
    { field: "code", headerName: "Code", width: 120 },
    { field: "name", headerName: "Name", width: 200 },
    { field: "skills", headerName: "Skills", width: 200, valueGetter: (value: unknown) => Array.isArray(value) ? (value as string[]).join(", ") : String(value) },
    { field: "experience", headerName: "Experience", width: 120 },
    { field: "score", headerName: "Score", width: 100 },
    { field: "status", headerName: "Status", width: 120 },
    {
      field: "actions",
      headerName: "Actions",
      width: 120,
      renderCell: (params) => (
        <IconButton
          color="primary"
          onClick={() => navigate(`/recruiter/evaluate/${params.row.id}`)}
          title="Evaluate Candidate"
        >
          <EditIcon />
        </IconButton>
      ),
    },
  ];

  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={2}>
        <Typography variant="h4">Candidates</Typography>
        <Button variant="contained" onClick={() => navigate("/recruiter/candidates/create")}>
          Add Candidate
        </Button>
      </Box>
      <DataGrid rows={candidates} columns={columns} getRowId={(row) => row.id} />
    </Box>
  );
}