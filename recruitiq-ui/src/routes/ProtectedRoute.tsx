import type { PropsWithChildren } from "react";
import { Navigate } from "react-router-dom";
import { isMock } from "../api/apiClient";

type ProtectedRouteProps = {
  allowedRoles?: string[];
};

export default function ProtectedRoute({
  children,
  allowedRoles,
}: PropsWithChildren<ProtectedRouteProps>) {
  const token = sessionStorage.getItem("accessToken");
  const role = sessionStorage.getItem("role");

  // no token → force login
  if (!token) {
    return <Navigate to="/login" replace />;
  }

  // ✅ MOCK MODE (NO JWT PARSING)
  if (isMock) {
    if (allowedRoles && role && !allowedRoles.includes(role)) {
      return <Navigate to="/login" replace />;
    }
    return <>{children}</>;
  }

  // ✅ REAL MODE (JWT VALIDATION)
  try {
    const payload = parseJwt(token);

    if (!payload) {
      sessionStorage.clear();
      return <Navigate to="/login" replace />;
    }

    const exp = payload.exp as number | undefined;
    // eslint-disable-next-line react-hooks/purity
    if (exp && Date.now() / 1000 >= exp) {
      sessionStorage.clear();
      return <Navigate to="/login" replace />;
    }

    if (allowedRoles) {
      const jwtRole = payload.role;
      if (!allowedRoles.includes(jwtRole)) {
        return <Navigate to="/login" replace />;
      }
    }

    return <>{children}</>;
  } catch {
    sessionStorage.clear();
    return <Navigate to="/login" replace />;
  }
}

// used only in REAL mode
function parseJwt(token: string) {
  const payload = token.split(".")[1];
  return JSON.parse(atob(payload));
}
