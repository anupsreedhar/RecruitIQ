// src/api/mock/candidates.mock.ts

export type MockCandidate = {
  id: string;
  code: string;
  name: string;
  skills: string[];
  experience: number;
  score: number;
  status: "NEW" | "EVALUATED" | "INTERVIEWED";
  resumeUploaded?: boolean;
  resumeFileName?: string;
};

const candidates: MockCandidate[] = [
  {
    id: "c1",
    code: "CAND-001",
    name: "Arjun Kumar",
    skills: ["Java", "Spring Boot", "Microservices"],
    experience: 6,
    score: 82,
    status: "EVALUATED",
    resumeUploaded: true,
    resumeFileName: "Arjun_Kumar_Resume.pdf",
  },
  {
    id: "c2",
    code: "CAND-002",
    name: "Meera Nair",
    skills: ["React", "TypeScript", "MUI"],
    experience: 4,
    score: 90,
    status: "NEW",
    resumeUploaded: true,
    resumeFileName: "Meera_Resume.pdf",
  },
  {
    id: "c3",
    code: "CAND-003",
    name: "Rajesh Sharma",
    skills: ["Python", "Django", "PostgreSQL"],
    experience: 8,
    score: 88,
    status: "INTERVIEWED",
     resumeUploaded: true,
    resumeFileName: "Rajesh_Sharma_Resume.pdf",
  },
  {
    id: "c4",
    code: "CAND-004",
    name: "Priya Singh",
    skills: ["JavaScript", "Node.js", "Express"],
    experience: 3,
    score: 75,
    status: "NEW",
    resumeUploaded: true,
    resumeFileName: "Priya_Singh_Resume.pdf",  },
  {
    id: "c5",
    code: "CAND-005",
    name: "Vikram Patel",
    skills: ["C#", ".NET", "Azure"],
    experience: 7,
    score: 85,
    status: "EVALUATED",
    resumeUploaded: true,
    resumeFileName: "Vikram_Patel_Resume.pdf",
  },
  {
    id: "c6",
    code: "CAND-006",
    name: "Anjali Gupta",
    skills: ["Angular", "RxJS", "SCSS"],
    experience: 5,
    score: 92,
    status: "INTERVIEWED",
    resumeUploaded: true,
    resumeFileName: "Anjali_Gupta_Resume.pdf",
  },
  {
    id: "c7",
    code: "CAND-007",
    name: "Suresh Reddy",
    skills: ["Go", "Kubernetes", "Docker"],
    experience: 9,
    score: 95,
    status: "NEW",
    resumeUploaded: true,
    resumeFileName: "Suresh_Reddy_Resume.pdf",
  },
  {
    id: "c8",
    code: "CAND-008",
    name: "Kavita Joshi",
    skills: ["PHP", "Laravel", "MySQL"],
    experience: 4,
    score: 78,
    status: "EVALUATED",
    resumeUploaded: true,
    resumeFileName: "Kavita_Joshi_Resume.pdf",
  },
  {
    id: "c9",
    code: "CAND-009",
    name: "Amit Verma",
    skills: ["Ruby", "Rails", "Redis"],
    experience: 6,
    score: 83,
    status: "NEW",
    resumeUploaded: true,
    resumeFileName: "Amit_Verma_Resume.pdf",
  },
  {
    id: "c10",
    code: "CAND-010",
    name: "Sneha Agarwal",
    skills: ["Swift", "iOS", "Core Data"],
    experience: 5,
    score: 87,
    status: "INTERVIEWED",
    resumeUploaded: true,
    resumeFileName: "Sneha_Agarwal_Resume.pdf",
  },
];

export const mockCandidatesApi = {
  list: async (): Promise<MockCandidate[]> => {
    await delay(400);
    return candidates;
  },

  getById: async (id: string): Promise<MockCandidate | undefined> => {
    await delay(300);
    return candidates.find((c) => c.id === id);
  },

  create: async (data: unknown): Promise<MockCandidate> => {
    await delay(500);
    const candidateData = data as Partial<MockCandidate>;
    const newCandidate: MockCandidate = {
      id: `c${Date.now()}`,
      code: `CAND-${Math.floor(Math.random() * 1000)}`,
      name: candidateData.name || "New Candidate",
      skills: candidateData.skills || [],
      experience: candidateData.experience || 0,
      score: 0,
      status: "NEW",
    };
    candidates.push(newCandidate);
    return newCandidate;
  },

  uploadResume: async (candidateId: string, file: File): Promise<MockCandidate | undefined> => {
    await delay(300);
    const candidate = candidates.find((c) => c.id === candidateId);
    if (candidate) {
      candidate.resumeUploaded = true; // just a mock flag
      candidate.resumeFileName = file.name;
    }
    return candidates;
  }
};

const delay = (ms: number) =>
  new Promise((resolve) => setTimeout(resolve, ms));
