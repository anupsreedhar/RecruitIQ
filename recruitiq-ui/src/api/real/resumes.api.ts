const API_BASE = import.meta.env.VITE_API_BASE_URL;

export const resumesApi = {
  getByCandidateId: async (candidateId: string) => {
    const res = await fetch(`${API_BASE}/api/v1/resumes/candidate/${candidateId}`, {
      headers: { Authorization: `Bearer ${sessionStorage.getItem("accessToken")}` },
    });
    if (!res.ok) throw new Error("Failed to fetch resumes for candidate");
    return res.json();
  },

  create: async (candidateId: string, data: unknown) => {
    const res = await fetch(`${API_BASE}/api/v1/resumes/candidate/${candidateId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
      },
      body: JSON.stringify(data),
    });
    if (!res.ok) throw new Error("Failed to create resume");
    return res.json();
  },
};