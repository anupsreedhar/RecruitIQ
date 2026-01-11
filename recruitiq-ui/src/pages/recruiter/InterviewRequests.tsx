import { useState } from "react";
import {
  Box,
  Typography,
  Button,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  Chip,
  IconButton,
} from "@mui/material";
import { Add as AddIcon, Visibility as VisibilityIcon } from "@mui/icons-material";

// Mock data
const mockCandidates = [
  { id: "c1", name: "John Doe", code: "JD001" },
  { id: "c2", name: "Jane Smith", code: "JS002" },
  { id: "c3", name: "Bob Johnson", code: "BJ003" },
  { id: "c4", name: "Alice Brown", code: "AB004" },
];

const mockPositions = [
  { id: "p1", name: "Senior Software Engineer" },
  { id: "p2", name: "Product Manager" },
  { id: "p3", name: "UX Designer" },
  { id: "p4", name: "DevOps Engineer" },
];

const mockInterviewRounds = [
  { id: "r1", name: "Technical Round 1", interviewer: "Sarah Wilson", status: "COMPLETED" as const, feedback: "Good technical skills" },
  { id: "r2", name: "Technical Round 2", interviewer: "Mike Chen", status: "COMPLETED" as const, feedback: "Excellent problem solving" },
  { id: "r3", name: "HR Round", interviewer: "Lisa Davis", status: "PENDING" as const, feedback: "" },
];

const mockInterviewRequests: InterviewRequest[] = [
  {
    id: "ir1",
    candidateId: "c1",
    positionId: "p1",
    totalRounds: 3,
    completedRounds: 2,
    currentRoundName: "HR Round",
    status: "IN_PROGRESS",
    scheduledDate: "2024-01-15",
    notes: "Strong candidate for technical roles",
    rounds: mockInterviewRounds,
  },
  {
    id: "ir2",
    candidateId: "c2",
    positionId: "p2",
    totalRounds: 2,
    completedRounds: 0,
    currentRoundName: "Technical Round 1",
    status: "SCHEDULED",
    scheduledDate: "2024-01-18",
    notes: "Product management experience",
    rounds: [
      { id: "r4", name: "Technical Round 1", interviewer: "Tom Anderson", status: "PENDING", feedback: "" },
      { id: "r5", name: "HR Round", interviewer: "Lisa Davis", status: "PENDING", feedback: "" },
    ],
  },
  {
    id: "ir3",
    candidateId: "c3",
    positionId: "p3",
    totalRounds: 2,
    completedRounds: 2,
    currentRoundName: "HR Round",
    status: "COMPLETED",
    scheduledDate: "2024-01-10",
    notes: "Creative design portfolio",
    rounds: [
      { id: "r6", name: "Design Review", interviewer: "Emma Taylor", status: "COMPLETED", feedback: "Outstanding portfolio" },
      { id: "r7", name: "HR Round", interviewer: "Lisa Davis", status: "COMPLETED", feedback: "Good cultural fit" },
    ],
  },
];

type InterviewStatus = "NEW" | "SCHEDULED" | "IN_PROGRESS" | "COMPLETED" | "REJECTED" | "SELECTED";

const getStatusColor = (status: InterviewStatus) => {
  switch (status) {
    case "NEW": return "default";
    case "SCHEDULED": return "info";
    case "IN_PROGRESS": return "warning";
    case "COMPLETED": return "success";
    case "REJECTED": return "error";
    case "SELECTED": return "success";
    default: return "default";
  }
};

interface InterviewRequest {
  id: string;
  candidateId: string;
  positionId: string;
  totalRounds: number;
  completedRounds: number;
  currentRoundName: string;
  status: InterviewStatus;
  scheduledDate: string;
  notes: string;
  rounds: Array<{
    id: string;
    name: string;
    interviewer: string;
    status: "PENDING" | "COMPLETED";
    feedback: string;
  }>;
}

export default function InterviewRequests() {
  const [interviewRequests, setInterviewRequests] = useState<InterviewRequest[]>(mockInterviewRequests);
  const [createDialogOpen, setCreateDialogOpen] = useState(false);
  const [viewDialogOpen, setViewDialogOpen] = useState(false);
  const [selectedRequest, setSelectedRequest] = useState<InterviewRequest | null>(null);
  const [formData, setFormData] = useState({
    candidateId: "",
    positionId: "",
    totalRounds: 1,
    scheduledDate: "",
    notes: "",
  });

  const handleCreateDialogOpen = () => {
    setCreateDialogOpen(true);
  };

  const handleCreateDialogClose = () => {
    setCreateDialogOpen(false);
    setFormData({
      candidateId: "",
      positionId: "",
      totalRounds: 1,
      scheduledDate: "",
      notes: "",
    });
  };

  const handleViewDialogOpen = (request: InterviewRequest) => {
    setSelectedRequest(request);
    setViewDialogOpen(true);
  };

  const handleViewDialogClose = () => {
    setViewDialogOpen(false);
    setSelectedRequest(null);
  };

  const handleCreateRequest = () => {
    if (!formData.candidateId || !formData.positionId || !formData.scheduledDate) {
      return;
    }

    const candidate = mockCandidates.find(c => c.id === formData.candidateId);
    const position = mockPositions.find(p => p.id === formData.positionId);

    if (!candidate || !position) return;

    const newRequest: InterviewRequest = {
      id: `ir${Date.now()}`,
      candidateId: formData.candidateId,
      positionId: formData.positionId,
      totalRounds: formData.totalRounds,
      completedRounds: 0,
      currentRoundName: `Round 1`,
      status: "NEW",
      scheduledDate: formData.scheduledDate,
      notes: formData.notes,
      rounds: Array.from({ length: formData.totalRounds }, (_, i) => ({
        id: `r${Date.now() + i}`,
        name: `Round ${i + 1}`,
        interviewer: "",
        status: "PENDING" as const,
        feedback: "",
      })),
    };

    setInterviewRequests(prev => [...prev, newRequest]);
    handleCreateDialogClose();
  };

  const getCandidateName = (candidateId: string) => {
    return mockCandidates.find(c => c.id === candidateId)?.name || "Unknown";
  };

  const getCandidateCode = (candidateId: string) => {
    return mockCandidates.find(c => c.id === candidateId)?.code || "Unknown";
  };

  const getPositionName = (positionId: string) => {
    return mockPositions.find(p => p.id === positionId)?.name || "Unknown";
  };

  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Typography variant="h4">Interview Requests</Typography>
        <Button
          variant="contained"
          startIcon={<AddIcon />}
          onClick={handleCreateDialogOpen}
        >
          Create Interview Request
        </Button>
      </Box>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Candidate Name</TableCell>
              <TableCell>Candidate Code</TableCell>
              <TableCell>Position Name</TableCell>
              <TableCell align="center">Total Rounds</TableCell>
              <TableCell align="center">Completed Rounds</TableCell>
              <TableCell>Current Round</TableCell>
              <TableCell>Status</TableCell>
              <TableCell>Scheduled Date</TableCell>
              <TableCell align="center">Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {interviewRequests.map((request) => (
              <TableRow key={request.id}>
                <TableCell>{getCandidateName(request.candidateId)}</TableCell>
                <TableCell>{getCandidateCode(request.candidateId)}</TableCell>
                <TableCell>{getPositionName(request.positionId)}</TableCell>
                <TableCell align="center">{request.totalRounds}</TableCell>
                <TableCell align="center">{request.completedRounds}</TableCell>
                <TableCell>{request.currentRoundName}</TableCell>
                <TableCell>
                  <Chip
                    label={request.status}
                    color={getStatusColor(request.status)}
                    size="small"
                  />
                </TableCell>
                <TableCell>{request.scheduledDate}</TableCell>
                <TableCell align="center">
                  <IconButton
                    color="primary"
                    onClick={() => handleViewDialogOpen(request)}
                    title="View Details"
                  >
                    <VisibilityIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {/* Create Interview Request Dialog */}
      <Dialog open={createDialogOpen} onClose={handleCreateDialogClose} maxWidth="sm" fullWidth>
        <DialogTitle>Create Interview Request</DialogTitle>
        <DialogContent>
          <Box display="flex" flexDirection="column" gap={2} sx={{ mt: 1 }}>
            <FormControl fullWidth>
              <InputLabel>Candidate</InputLabel>
              <Select
                value={formData.candidateId}
                label="Candidate"
                onChange={(e) => setFormData(prev => ({ ...prev, candidateId: e.target.value }))}
              >
                {mockCandidates.map((candidate) => (
                  <MenuItem key={candidate.id} value={candidate.id}>
                    {candidate.name} ({candidate.code})
                  </MenuItem>
                ))}
              </Select>
            </FormControl>

            <FormControl fullWidth>
              <InputLabel>Position</InputLabel>
              <Select
                value={formData.positionId}
                label="Position"
                onChange={(e) => setFormData(prev => ({ ...prev, positionId: e.target.value }))}
              >
                {mockPositions.map((position) => (
                  <MenuItem key={position.id} value={position.id}>
                    {position.name}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>

            <Box display="flex" gap={2}>
              <TextField
                fullWidth
                label="Number of Rounds"
                type="number"
                value={formData.totalRounds}
                onChange={(e) => setFormData(prev => ({ ...prev, totalRounds: parseInt(e.target.value) || 1 }))}
                inputProps={{ min: 1, max: 5 }}
              />

              <TextField
                fullWidth
                label="Interview Start Date"
                type="date"
                value={formData.scheduledDate}
                onChange={(e) => setFormData(prev => ({ ...prev, scheduledDate: e.target.value }))}
                InputLabelProps={{ shrink: true }}
              />
            </Box>

            <TextField
              fullWidth
              label="Notes (Optional)"
              multiline
              rows={3}
              value={formData.notes}
              onChange={(e) => setFormData(prev => ({ ...prev, notes: e.target.value }))}
            />
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCreateDialogClose}>Cancel</Button>
          <Button
            onClick={handleCreateRequest}
            variant="contained"
            disabled={!formData.candidateId || !formData.positionId || !formData.scheduledDate}
          >
            Create Request
          </Button>
        </DialogActions>
      </Dialog>

      {/* View Interview Details Dialog */}
      <Dialog open={viewDialogOpen} onClose={handleViewDialogClose} maxWidth="md" fullWidth>
        <DialogTitle>
          Interview Details - {selectedRequest && getCandidateName(selectedRequest.candidateId)}
        </DialogTitle>
        <DialogContent>
          {selectedRequest && (
            <Box>
              <Typography variant="h6" gutterBottom>
                Interview Rounds
              </Typography>
              <TableContainer component={Paper} sx={{ mt: 2 }}>
                <Table size="small">
                  <TableHead>
                    <TableRow>
                      <TableCell>Round Name</TableCell>
                      <TableCell>Interviewer</TableCell>
                      <TableCell>Status</TableCell>
                      <TableCell>Feedback</TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {selectedRequest.rounds.map((round) => (
                      <TableRow key={round.id}>
                        <TableCell>{round.name}</TableCell>
                        <TableCell>{round.interviewer || "Not Assigned"}</TableCell>
                        <TableCell>
                          <Chip
                            label={round.status}
                            color={round.status === "COMPLETED" ? "success" : "default"}
                            size="small"
                          />
                        </TableCell>
                        <TableCell>{round.feedback || "No feedback yet"}</TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </TableContainer>

              {selectedRequest.notes && (
                <Box sx={{ mt: 3 }}>
                  <Typography variant="h6" gutterBottom>
                    Notes
                  </Typography>
                  <Typography variant="body2" color="text.secondary">
                    {selectedRequest.notes}
                  </Typography>
                </Box>
              )}
            </Box>
          )}
        </DialogContent>
        <DialogActions>
          <Button onClick={handleViewDialogClose}>Close</Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
}