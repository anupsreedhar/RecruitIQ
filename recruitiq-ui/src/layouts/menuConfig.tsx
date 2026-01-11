import type { ReactNode } from "react";
import DashboardIcon from "@mui/icons-material/Dashboard";
import PeopleIcon from "@mui/icons-material/People";
import AddIcon from "@mui/icons-material/Add";
import AssignmentIcon from "@mui/icons-material/Assignment";
import BusinessIcon from "@mui/icons-material/Business";
import EventIcon from "@mui/icons-material/Event";

export type MenuItem = {
  label: string;
  path: string;
  icon: ReactNode;
};

export const menuConfig: Record<string, MenuItem[]> = {
  AGENCY_ADMIN: [
    { label: "Dashboard", path: "/admin/dashboard", icon: <DashboardIcon /> },
    { label: "Users", path: "/admin/users", icon: <PeopleIcon /> },
    { label: "Vendors", path: "/admin/vendors", icon: <BusinessIcon /> },
  ],

  RECRUITER: [
    { label: "Dashboard", path: "/recruiter/dashboard", icon: <DashboardIcon /> },
    { label: "Candidates", path: "/recruiter/candidates", icon: <PeopleIcon /> },
    { label: "Create Candidate", path: "/recruiter/candidates/create", icon: <AddIcon /> },
    { label: "Interview Requests", path: "/recruiter/interviews", icon: <EventIcon /> },
  ],

  VENDOR_USER: [
    { label: "Dashboard", path: "/vendor/dashboard", icon: <DashboardIcon /> },
    { label: "Assigned Candidates", path: "/vendor/candidates", icon: <AssignmentIcon /> },
  ],
};
