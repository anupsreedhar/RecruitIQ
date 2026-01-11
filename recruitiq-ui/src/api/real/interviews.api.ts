const API_BASE = import.meta.env.VITE_API_BASE_URL;

export const interviewsApi = {
  list: async () => {
    const res = await fetch(`${API_BASE}/api/v1/interviews`, {
      headers: { Authorization: `Bearer ${sessionStorage.getItem("accessToken")}` },
    });
    if (!res.ok) throw new Error("Failed to fetch interviews");
    return res.json();
  },

  create: async (data: unknown) => {
    const res = await fetch(`${API_BASE}/api/v1/interviews`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
      },
      body: JSON.stringify(data),
    });
    if (!res.ok) throw new Error("Failed to create interview");
    return res.json();
  },

  updateStatus: async (requestId: string, status: unknown) => {
    const res = await fetch(`${API_BASE}/api/v1/interviews/${requestId}/status`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
      },
      body: JSON.stringify(status),
    });
    if (!res.ok) throw new Error("Failed to update interview status");
    return res.json();
  },
};