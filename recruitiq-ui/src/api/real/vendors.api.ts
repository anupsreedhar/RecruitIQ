const API_BASE = import.meta.env.VITE_API_BASE_URL;

export const vendorsApi = {
  list: async () => {
    const res = await fetch(`${API_BASE}/api/v1/vendors`, {
      headers: { Authorization: `Bearer ${sessionStorage.getItem("accessToken")}` },
    });
    if (!res.ok) throw new Error("Failed to fetch vendors");
    return res.json();
  },

  getById: async (id: string) => {
    const res = await fetch(`${API_BASE}/api/v1/vendors/${id}`, {
      headers: { Authorization: `Bearer ${sessionStorage.getItem("accessToken")}` },
    });
    if (!res.ok) throw new Error("Failed to fetch vendor");
    return res.json();
  },

  create: async (data: unknown) => {
    const res = await fetch(`${API_BASE}/api/v1/vendors`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
      },
      body: JSON.stringify(data),
    });
    if (!res.ok) throw new Error("Failed to create vendor");
    return res.json();
  },
};