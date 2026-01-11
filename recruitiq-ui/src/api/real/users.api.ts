const API_BASE = import.meta.env.VITE_API_BASE_URL;

export const usersApi = {
  list: async () => {
    const res = await fetch(`${API_BASE}/api/v1/users`, {
      headers: { Authorization: `Bearer ${sessionStorage.getItem("accessToken")}` },
    });
    if (!res.ok) throw new Error("Failed to fetch users");
    return res.json();
  },

  getById: async (id: string) => {
    const res = await fetch(`${API_BASE}/api/v1/users/${id}`, {
      headers: { Authorization: `Bearer ${sessionStorage.getItem("accessToken")}` },
    });
    if (!res.ok) throw new Error("Failed to fetch user");
    return res.json();
  },

  create: async (data: unknown) => {
    const res = await fetch(`${API_BASE}/api/v1/users`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
      },
      body: JSON.stringify(data),
    });
    if (!res.ok) throw new Error("Failed to create user");
    return res.json();
  },

  updateStatus: async (userId: string, status: unknown) => {
    const res = await fetch(`${API_BASE}/api/v1/users/${userId}/status`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
      },
      body: JSON.stringify(status),
    });
    if (!res.ok) throw new Error("Failed to update user status");
    return res.json();
  },
};