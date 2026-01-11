const API_BASE = import.meta.env.VITE_API_BASE_URL;

export const positionsApi = {
  list: async () => {
    const res = await fetch(`${API_BASE}/api/v1/positions`, {
      headers: { Authorization: `Bearer ${sessionStorage.getItem("accessToken")}` },
    });
    if (!res.ok) throw new Error("Failed to fetch positions");
    return res.json();
  },

  getById: async (id: string) => {
    const res = await fetch(`${API_BASE}/api/v1/positions/${id}`, {
      headers: { Authorization: `Bearer ${sessionStorage.getItem("accessToken")}` },
    });
    if (!res.ok) throw new Error("Failed to fetch position");
    return res.json();
  },

  create: async (data: unknown) => {
    const res = await fetch(`${API_BASE}/api/v1/positions`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
      },
      body: JSON.stringify(data),
    });
    if (!res.ok) throw new Error("Failed to create position");
    return res.json();
  },

  updateStatus: async (positionId: string, status: unknown) => {
    const res = await fetch(`${API_BASE}/api/v1/positions/${positionId}/status`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
      },
      body: JSON.stringify(status),
    });
    if (!res.ok) throw new Error("Failed to update position status");
    return res.json();
  },
};