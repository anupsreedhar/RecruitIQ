// src/api/mock/evaluations.mock.ts

export type MockEvaluation = {
  id: string;
  candidateId: string;
  evaluator: string;
  score: number;
  remarks: string;
};

const evaluations: MockEvaluation[] = [
  {
    id: "e1",
    candidateId: "c1",
    evaluator: "Recruiter User",
    score: 82,
    remarks: "Strong backend fundamentals",
  },
];

export const mockEvaluationsApi = {
  listByCandidate: async (
    candidateId: string
  ): Promise<MockEvaluation[]> => {
    await delay(300);
    return evaluations.filter((e) => e.candidateId === candidateId);
  },

  create: async (evaluation: Partial<MockEvaluation>): Promise<MockEvaluation> => {
    await delay(500);
    const newEvaluation: MockEvaluation = {
      id: `e${Date.now()}`,
      candidateId: evaluation.candidateId!,
      evaluator: evaluation.evaluator || "System",
      score: evaluation.score || 0,
      remarks: evaluation.remarks || "",
    };
    evaluations.push(newEvaluation);
    return newEvaluation;
  },
};

const delay = (ms: number) =>
  new Promise((resolve) => setTimeout(resolve, ms));
