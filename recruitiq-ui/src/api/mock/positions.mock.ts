// src/api/mock/positions.mock.ts

export type MockPosition = {
  id: string;
  title: string;
  skillsRequired: string[];
  experienceRequired: number;
  status: "OPEN" | "CLOSED";
};

const positions: MockPosition[] = [
  {
    id: "p1",
    title: "Senior Java Developer",
    skillsRequired: ["Java", "Spring Boot", "Kafka"],
    experienceRequired: 5,
    status: "OPEN",
  },
  {
    id: "p2",
    title: "Frontend Engineer",
    skillsRequired: ["React", "TypeScript"],
    experienceRequired: 3,
    status: "OPEN",
  },
];

export const mockPositionsApi = {
  list: async (): Promise<MockPosition[]> => {
    await delay(400);
    return positions;
  },

  create: async (position: Partial<MockPosition>): Promise<MockPosition> => {
    await delay(500);
    const newPosition: MockPosition = {
      id: `p${Date.now()}`,
      title: position.title || "New Position",
      skillsRequired: position.skillsRequired || [],
      experienceRequired: position.experienceRequired || 0,
      status: "OPEN",
    };
    positions.push(newPosition);
    return newPosition;
  },
};

const delay = (ms: number) =>
  new Promise((resolve) => setTimeout(resolve, ms));
