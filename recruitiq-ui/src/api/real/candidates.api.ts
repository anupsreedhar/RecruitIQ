const API_BASE = import.meta.env.VITE_API_BASE_URL;

export const candidatesApi = {
  list: async () => {
    const res = await fetch(`${API_BASE}/api/v1/candidates`, {
      headers: { Authorization: `Bearer ${sessionStorage.getItem("accessToken")}` },
    });
    if (!res.ok) throw new Error("Failed to fetch candidates");
    return res.json();
  },

  getById: async (id: string) => {
    const res = await fetch(`${API_BASE}/api/v1/candidates/${id}`, {
      headers: { Authorization: `Bearer ${sessionStorage.getItem("accessToken")}` },
    });
    if (!res.ok) throw new Error("Failed to fetch candidate");
    return res.json();
  },

  create: async (data: unknown) => {
    const isFormData = data instanceof FormData;
    const res = await fetch(`${API_BASE}/api/v1/candidates`, {
      method: "POST",
      headers: isFormData
        ? { Authorization: `Bearer ${sessionStorage.getItem("accessToken")}` }
        : {
            "Content-Type": "application/json",
            Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
          },
      body: isFormData ? data : JSON.stringify(data),
    });
    if (!res.ok) throw new Error("Failed to create candidate");
    return res.json();
  },

  uploadResume: async (candidateId: string, file: File) => {
    const formData = new FormData();
    formData.append("file", file);

    const res = await fetch(
      `${API_BASE}/api/v1/resumes/candidate/${candidateId}`,
      {
        method: "POST",
        headers: {
          Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
        },
        body: formData,
      }
    );

    if (!res.ok) throw new Error("Failed to upload resume");
    return res.json();
  },

  getByCode: async (candidateCode: string) => {
    const res = await fetch(`${API_BASE}/api/v1/candidates/by-code/${candidateCode}`, {
      headers: { Authorization: `Bearer ${sessionStorage.getItem("accessToken")}` },
    });
    if (!res.ok) throw new Error("Failed to fetch candidate by code");
    return res.json();
  },

  updateSkills: async (candidateId: string, skills: unknown) => {
    const res = await fetch(`${API_BASE}/api/v1/candidates/${candidateId}/skills`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
      },
      body: JSON.stringify(skills),
    });
    if (!res.ok) throw new Error("Failed to update candidate skills");
    return res.json();
  },
};