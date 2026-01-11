import { useEffect, useState } from "react";
import { DataGrid } from "@mui/x-data-grid";
import type { GridColDef } from "@mui/x-data-grid";
import { Box, Typography } from "@mui/material";
import { apiClient } from "../../api/apiClient";

export default function Vendors() {
  const [vendors, setVendors] = useState([]);

  useEffect(() => {
    apiClient.vendors.list().then(setVendors);
  }, []);

  const columns: GridColDef[] = [
    { field: "id", headerName: "ID", width: 120 },
    { field: "name", headerName: "Name", width: 200 },
    { field: "contactEmail", headerName: "Contact Email", width: 250 },
    { field: "active", headerName: "Active", width: 100, type: "boolean" },
  ];

  return (
    <Box>
      <Typography variant="h4" gutterBottom>Vendors Management</Typography>
      <DataGrid rows={vendors} columns={columns} getRowId={(row) => row.id} />
    </Box>
  );
}