import { authMockApi } from "./mock/auth.mock";
import { authApi } from "./real/auth.api";
import { mockCandidatesApi } from "./mock/candidates.mock";
import { candidatesApi } from "./real/candidates.api";
import { mockEvaluationsApi } from "./mock/evaluations.mock";
import { evaluationsApi } from "./real/evaluations.api";
import { mockPositionsApi } from "./mock/positions.mock";
import { positionsApi } from "./real/positions.api";
import { mockVendorsApi } from "./mock/vendors.mock";
import { vendorsApi } from "./real/vendors.api";
import { interviewsApi } from "./real/interviews.api";
import { resumesApi } from "./real/resumes.api";
import { usersApi } from "./real/users.api";
import { vendorVisibilityApi } from "./real/vendor-visibility.api";

const USE_MOCK = import.meta.env.VITE_USE_MOCK === "true";

console.log("[API_CLIENT] USE_MOCK:", USE_MOCK);

export const isMock = USE_MOCK;

export const apiClient = {
  auth: USE_MOCK ? authMockApi : authApi,
  candidates: USE_MOCK ? mockCandidatesApi : candidatesApi,
  evaluations: USE_MOCK ? mockEvaluationsApi : evaluationsApi,
  positions: USE_MOCK ? mockPositionsApi : positionsApi,
  vendors: USE_MOCK ? mockVendorsApi : vendorsApi,
  interviews: interviewsApi,
  resumes: resumesApi,
  users: usersApi,
  vendorVisibility: vendorVisibilityApi,
};
