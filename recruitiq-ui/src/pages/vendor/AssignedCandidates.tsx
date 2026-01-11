import { useEffect, useState } from "react";
import { DataGrid } from "@mui/x-data-grid";
import type { GridColDef } from "@mui/x-data-grid";
import { Box, Typography } from "@mui/material";
import { candidateService } from "../../services/candidateService";

export default function AssignedCandidates() {
  const [candidates, setCandidates] = useState([]);

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
  ];

  return (
    <Box>
      <Typography variant="h4" gutterBottom>Assigned Candidates</Typography>
      <DataGrid rows={candidates} columns={columns} getRowId={(row) => row.id} />
    </Box>
  );
}