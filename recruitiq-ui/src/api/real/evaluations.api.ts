const API_BASE = import.meta.env.VITE_API_BASE_URL;

export const evaluationsApi = {
  getByCandidateId: async (candidateId: string) => {
    const res = await fetch(`${API_BASE}/api/v1/evaluations/candidate/${candidateId}`, {
      headers: { Authorization: `Bearer ${sessionStorage.getItem("accessToken")}` },
    });
    if (!res.ok) throw new Error("Failed to fetch evaluations for candidate");
    return res.json();
  },

  create: async (data: unknown) => {
    const res = await fetch(`${API_BASE}/api/v1/evaluations`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
      },
      body: JSON.stringify(data),
    });
    if (!res.ok) throw new Error("Failed to create evaluation");
    return res.json();
  },
};