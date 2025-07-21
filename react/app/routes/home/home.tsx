import { data, useLoaderData } from "react-router";
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "~/components/ui/card";
import { Button } from "~/components/ui/button";
import { Badge } from "~/components/ui/badge";
import { Avatar, AvatarFallback, AvatarImage } from "~/components/ui/avatar";
import {
  Users,
  Settings,
  GitBranch,
  Server,
  Rocket,
  ExternalLink,
  CheckCircle,
  XCircle,
  AlertCircle,
  Clock
} from "lucide-react";
import type { UserPresenter } from "~/gen/models/userPresenter";
import { me } from "~/gen/user-controller/user-controller";

// Mock data with technical details
const mockProjects = [
  {
    id: 1,
    name: "E-commerce Platform",
    description: "Modern e-commerce solution with React and Node.js",
    status: "active",
    lastUpdated: "2024-03-15",
    repository: {
      url: "https://github.com/company/ecommerce-platform",
      branch: "main",
      lastCommit: "2024-03-15T10:30:00Z"
    },
    deployments: [
      { environment: "production", status: "healthy", url: "https://shop.company.com", lastDeploy: "2024-03-14" },
      { environment: "staging", status: "healthy", url: "https://staging-shop.company.com", lastDeploy: "2024-03-15" }
    ],
    servers: [
      { name: "prod-web-01", status: "online", cpu: 45, memory: 62 },
      { name: "prod-web-02", status: "online", cpu: 38, memory: 58 },
      { name: "prod-db-01", status: "online", cpu: 72, memory: 84 }
    ],
    team: [
      { id: 1, name: "Alice Johnson", role: "Tech Lead", avatar: "/avatars/alice.jpg" },
      { id: 2, name: "Bob Smith", role: "Frontend Dev", avatar: "/avatars/bob.jpg" },
      { id: 3, name: "Carol Davis", role: "Backend Dev", avatar: "/avatars/carol.jpg" },
      { id: 4, name: "David Wilson", role: "DevOps", avatar: "/avatars/david.jpg" }
    ]
  },
  {
    id: 2,
    name: "Mobile Banking App",
    description: "Secure mobile banking application with biometric authentication",
    status: "in-progress",
    lastUpdated: "2024-03-20",
    repository: {
      url: "https://github.com/company/mobile-banking",
      branch: "develop",
      lastCommit: "2024-03-20T14:15:00Z"
    },
    deployments: [
      { environment: "staging", status: "healthy", url: "https://staging-bank.company.com", lastDeploy: "2024-03-20" },
      { environment: "dev", status: "warning", url: "https://dev-bank.company.com", lastDeploy: "2024-03-19" }
    ],
    servers: [
      { name: "staging-app-01", status: "online", cpu: 23, memory: 41 },
      { name: "staging-db-01", status: "warning", cpu: 89, memory: 92 }
    ],
    team: [
      { id: 5, name: "Eva Martinez", role: "Mobile Lead", avatar: "/avatars/eva.jpg" },
      { id: 6, name: "Frank Brown", role: "iOS Dev", avatar: "/avatars/frank.jpg" },
      { id: 7, name: "Grace Lee", role: "Android Dev", avatar: "/avatars/grace.jpg" }
    ]
  },
  {
    id: 3,
    name: "Data Analytics Dashboard",
    description: "Real-time analytics dashboard for business intelligence",
    status: "completed",
    lastUpdated: "2024-02-28",
    repository: {
      url: "https://github.com/company/analytics-dashboard",
      branch: "main",
      lastCommit: "2024-02-28T09:45:00Z"
    },
    deployments: [
      { environment: "production", status: "healthy", url: "https://analytics.company.com", lastDeploy: "2024-02-28" }
    ],
    servers: [
      { name: "analytics-web-01", status: "online", cpu: 15, memory: 32 },
      { name: "analytics-db-01", status: "online", cpu: 28, memory: 45 }
    ],
    team: [
      { id: 8, name: "Henry Taylor", role: "Data Engineer", avatar: "/avatars/henry.jpg" },
      { id: 9, name: "Ivy Chen", role: "Frontend Dev", avatar: "/avatars/ivy.jpg" }
    ]
  },
  {
    id: 4,
    name: "Customer Support Portal",
    description: "Comprehensive customer support and ticketing system",
    status: "planning",
    lastUpdated: "2024-03-22",
    repository: {
      url: "https://github.com/company/support-portal",
      branch: "feature/initial-setup",
      lastCommit: "2024-03-22T16:20:00Z"
    },
    deployments: [
      { environment: "dev", status: "error", url: "https://dev-support.company.com", lastDeploy: "2024-03-21" }
    ],
    servers: [
      { name: "dev-support-01", status: "offline", cpu: 0, memory: 0 }
    ],
    team: [
      { id: 10, name: "Jack Wilson", role: "Full Stack", avatar: "/avatars/jack.jpg" },
      { id: 11, name: "Kate Anderson", role: "UX Designer", avatar: "/avatars/kate.jpg" }
    ]
  }
];

const statusColors: { [key: string]: string } = {
  active: "bg-green-100 text-green-800 dark:bg-green-900 dark:text-green-300",
  "in-progress": "bg-blue-100 text-blue-800 dark:bg-blue-900 dark:text-blue-300",
  completed: "bg-gray-100 text-gray-800 dark:bg-gray-800 dark:text-gray-300",
  planning: "bg-yellow-100 text-yellow-800 dark:bg-yellow-900 dark:text-yellow-300"
};

const deploymentStatusIcons: any = {
  healthy: <CheckCircle className="h-3 w-3 text-green-600" />,
  warning: <AlertCircle className="h-3 w-3 text-yellow-600" />,
  error: <XCircle className="h-3 w-3 text-red-600" />
};

export async function clientLoader() {
  const response = await me({ credentials: 'include' });
  return response.data
}

export default function Home() {
  const user = useLoaderData<UserPresenter>();

  const handleManageProject = (projectId: number) => {
    console.log(`Managing project ${projectId}`);
    // Navigate to project management page
  };

  const formatDate = (dateString: string) => {
    return new Date(dateString).toLocaleDateString('en-US', {
      month: 'short',
      day: 'numeric',
      year: 'numeric'
    });
  };

  const getInitials = (name: string) => {
    return name.split(' ').map(n => n[0]).join('').toUpperCase();
  };

  return (
    <div className="space-y-6">
      {/* Welcome Header */}
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-foreground">
          Welcome back, {user?.firstName || 'User'}!
        </h1>
        <p className="text-muted-foreground mt-2">
          Monitor your projects, deployments, and infrastructure status.
        </p>
      </div>

      {/* Projects Grid */}
      <div className="grid grid-cols-1 xl:grid-cols-2 gap-6">
        {mockProjects.map((project) => (
          <Card key={project.id} className="hover:shadow-lg transition-all duration-200">
            <CardHeader className="pb-4">
              <div className="flex items-start justify-between">
                <div className="space-y-1">
                  <CardTitle className="text-lg font-semibold leading-tight">
                    {project.name}
                  </CardTitle>
                  <Badge
                    variant="secondary"
                    className={`text-xs ${statusColors[project.status]}`}
                  >
                    {project.status.replace('-', ' ')}
                  </Badge>
                </div>
              </div>
            </CardHeader>

            <CardContent className="pb-4 space-y-4">
              <CardDescription className="text-sm">
                {project.description}
              </CardDescription>

              {/* Repository Info */}
              <div className="space-y-2">
                <div className="flex items-center justify-between">
                  <h4 className="text-sm font-medium flex items-center gap-2">
                    <GitBranch className="h-4 w-4" />
                    Repository
                  </h4>
                  <Button variant="ghost" size="sm" className="h-6 px-2">
                    <ExternalLink className="h-3 w-3" />
                  </Button>
                </div>
                <div className="text-xs text-muted-foreground">
                  <div>Branch: <span className="font-mono">{project.repository.branch}</span></div>
                  <div>Last commit: {formatDate(project.repository.lastCommit)}</div>
                </div>
              </div>

              {/* Deployments */}
              <div className="space-y-2">
                <h4 className="text-sm font-medium flex items-center gap-2">
                  <Rocket className="h-4 w-4" />
                  Deployments
                </h4>
                <div className="space-y-1">
                  {project.deployments.map((deployment, idx) => (
                    <div key={idx} className="flex items-center justify-between text-xs">
                      <div className="flex items-center gap-2">
                        {deploymentStatusIcons[deployment.status]}
                        <span className="font-mono">{deployment.environment}</span>
                      </div>
                      <div className="flex items-center gap-2">
                        <span className="text-muted-foreground">
                          {formatDate(deployment.lastDeploy)}
                        </span>
                        <Button variant="ghost" size="sm" className="h-4 w-4 p-0">
                          <ExternalLink className="h-3 w-3" />
                        </Button>
                      </div>
                    </div>
                  ))}
                </div>
              </div>

              {/* Servers */}
              <div className="space-y-2">
                <h4 className="text-sm font-medium flex items-center gap-2">
                  <Server className="h-4 w-4" />
                  Servers
                </h4>
                <div className="space-y-1">
                  {project.servers.map((server, idx) => (
                    <div key={idx} className="flex items-center justify-between text-xs">
                      <div className="flex items-center gap-2">
                        <div className={`h-2 w-2 rounded-full ${server.status === 'online' ? 'bg-green-500' :
                          server.status === 'warning' ? 'bg-yellow-500' : 'bg-red-500'
                          }`} />
                        <span className="font-mono">{server.name}</span>
                      </div>
                      <div className="text-muted-foreground">
                        CPU: {server.cpu}% | MEM: {server.memory}%
                      </div>
                    </div>
                  ))}
                </div>
              </div>

              {/* Team Members */}
              <div className="space-y-2">
                <h4 className="text-sm font-medium flex items-center gap-2">
                  <Users className="h-4 w-4" />
                  Team ({project.team.length})
                </h4>
                <div className="flex items-center gap-2">
                  {project.team.slice(0, 4).map((member) => (
                    <div key={member.id} className="flex items-center gap-1">
                      <Avatar className="h-6 w-6">
                        <AvatarImage src={member.avatar} alt={member.name} />
                        <AvatarFallback className="text-xs">
                          {getInitials(member.name)}
                        </AvatarFallback>
                      </Avatar>
                    </div>
                  ))}
                  {project.team.length > 4 && (
                    <div className="text-xs text-muted-foreground">
                      +{project.team.length - 4} more
                    </div>
                  )}
                </div>
              </div>

              {/* Last Updated */}
              <div className="flex items-center gap-1 text-xs text-muted-foreground pt-2 border-t">
                <Clock className="h-3 w-3" />
                <span>Updated {formatDate(project.lastUpdated)}</span>
              </div>
            </CardContent>

            <CardFooter className="pt-0">
              <Button
                onClick={() => handleManageProject(project.id)}
                className="w-full"
                variant="outline"
              >
                <Settings className="mr-2 h-4 w-4" />
                Manage Project
              </Button>
            </CardFooter>
          </Card>
        ))}
      </div>

      {/* Technical Overview Stats */}
      <div className="grid grid-cols-1 md:grid-cols-4 gap-4 mt-8">
        <Card>
          <CardContent className="p-4">
            <div className="text-center">
              <div className="text-2xl font-bold text-primary">
                {mockProjects.length}
              </div>
              <div className="text-sm text-muted-foreground">Total Projects</div>
            </div>
          </CardContent>
        </Card>

        <Card>
          <CardContent className="p-4">
            <div className="text-center">
              <div className="text-2xl font-bold text-green-600">
                {mockProjects.reduce((acc, p) => acc + p.deployments.filter(d => d.status === 'healthy').length, 0)}
              </div>
              <div className="text-sm text-muted-foreground">Healthy Deployments</div>
            </div>
          </CardContent>
        </Card>

        <Card>
          <CardContent className="p-4">
            <div className="text-center">
              <div className="text-2xl font-bold text-blue-600">
                {mockProjects.reduce((acc, p) => acc + p.servers.filter(s => s.status === 'online').length, 0)}
              </div>
              <div className="text-sm text-muted-foreground">Active Servers</div>
            </div>
          </CardContent>
        </Card>

        <Card>
          <CardContent className="p-4">
            <div className="text-center">
              <div className="text-2xl font-bold text-purple-600">
                {mockProjects.reduce((acc, p) => acc + p.team.length, 0)}
              </div>
              <div className="text-sm text-muted-foreground">Team Members</div>
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  );
}
