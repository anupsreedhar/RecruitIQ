import { apiClient } from "../api/apiClient";

export const candidateService = {
  getAll: () => apiClient.candidates.list(),
  getById: (id: string) => apiClient.candidates.getById(id),
  create: (data: Record<string, unknown> | FormData) => apiClient.candidates.create(data),
};