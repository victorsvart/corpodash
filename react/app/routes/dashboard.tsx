import { data, redirect, Outlet, useLoaderData } from "react-router";
import { NavLink } from "react-router";
import { me } from "~/gen/user-controller/user-controller";
import { Settings, LogOut, ChevronDown } from "lucide-react";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuSeparator, DropdownMenuTrigger } from "~/components/ui/dropdown-menu";
import { Button } from "~/components/ui/button";
import { Avatar, AvatarFallback, AvatarImage } from "~/components/ui/avatar";
import type { UserPresenter } from "~/gen/models/userPresenter";

export async function clientLoader() {
  const response = await me({ credentials: 'include' });
  if (response.status !== 200) {
    return redirect("/auth/signIn")
  }

  return response.data;
}

export default function Dashboard() {
  const user = useLoaderData<UserPresenter>();
  const handleLogout = async () => {
    // Add your logout logic here
    console.log("Logging out...");
  };

  const handleUserSettings = () => {
    // Navigate to user settings
    console.log("User settings...");
  };

  return (
    <div className="min-h-screen bg-background">
      {/* Navbar */}
      <nav className="border-b bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between h-16">
            {/* Left side - Navigation Links */}
            <div className="flex items-center space-x-8">
              <div className="flex-shrink-0">
                <h1 className="text-xl font-semibold">Corpodash</h1>
              </div>

              {/* Navigation Links */}
              <div className="hidden md:flex space-x-1">
                <NavLink
                  to="/dashboard"
                  end
                  className={({ isActive }) =>
                    `px-3 py-2 rounded-md text-sm font-medium transition-colors ${isActive
                      ? 'bg-secondary text-secondary-foreground'
                      : 'text-muted-foreground hover:text-foreground hover:bg-secondary/50'
                    }`
                  }
                >
                  Home
                </NavLink>
                <NavLink
                  to="/dashboard/teams"
                  className={({ isActive }) =>
                    `px-3 py-2 rounded-md text-sm font-medium transition-colors ${isActive
                      ? 'bg-secondary text-secondary-foreground'
                      : 'text-muted-foreground hover:text-foreground hover:bg-secondary/50'
                    }`
                  }
                >
                  Teams
                </NavLink>
                <NavLink
                  to="/dashboard/projects"
                  className={({ isActive }) =>
                    `px-3 py-2 rounded-md text-sm font-medium transition-colors ${isActive
                      ? 'bg-secondary text-secondary-foreground'
                      : 'text-muted-foreground hover:text-foreground hover:bg-secondary/50'
                    }`
                  }
                >
                  Projects
                </NavLink>
              </div>
            </div>

            {/* Right side - User Profile Dropdown */}
            <div className="flex items-center">
              <DropdownMenu>
                <DropdownMenuTrigger asChild>
                  <Button variant="ghost" className="relative h-10 w-10 rounded-full">
                    <Avatar className="h-9 w-9">
                      <AvatarImage src="/placeholder-avatar.jpg" alt="User" />
                      <AvatarFallback>
                        {((user?.firstName?.[0] ?? "") + (user?.lastName?.[0] ?? "?")).toUpperCase()}
                      </AvatarFallback>
                    </Avatar>
                  </Button>
                </DropdownMenuTrigger>
                <DropdownMenuContent className="w-56" align="end" forceMount>
                  <div className="flex items-center justify-start gap-2 p-2">
                    <div className="flex flex-col space-y-1 leading-none">
                      <p className="font-medium">John Doe</p>
                      <p className="w-[200px] truncate text-sm text-muted-foreground">
                        john.doe@example.com
                      </p>
                    </div>
                  </div>
                  <DropdownMenuSeparator />
                  <DropdownMenuItem onClick={handleUserSettings} className="cursor-pointer">
                    <Settings className="mr-2 h-4 w-4" />
                    <span>User Settings</span>
                  </DropdownMenuItem>
                  <DropdownMenuSeparator />
                  <DropdownMenuItem onClick={handleLogout} className="cursor-pointer text-red-600 focus:text-red-600">
                    <LogOut className="mr-2 h-4 w-4" />
                    <span>Logout</span>
                  </DropdownMenuItem>
                </DropdownMenuContent>
              </DropdownMenu>
            </div>

            {/* Mobile menu button */}
            <div className="md:hidden flex items-center">
              <Button variant="ghost" size="sm">
                <ChevronDown className="h-4 w-4" />
              </Button>
            </div>
          </div>

          {/* Mobile Navigation */}
          <div className="md:hidden pb-3 pt-2 space-y-1">
            <NavLink
              to="/dashboard"
              end
              className={({ isActive }) =>
                `block px-3 py-2 rounded-md text-sm font-medium ${isActive
                  ? 'bg-secondary text-secondary-foreground'
                  : 'text-muted-foreground hover:text-foreground hover:bg-secondary/50'
                }`
              }
            >
              Home
            </NavLink>
            <NavLink
              to="/dashboard/teams"
              className={({ isActive }) =>
                `block px-3 py-2 rounded-md text-sm font-medium ${isActive
                  ? 'bg-secondary text-secondary-foreground'
                  : 'text-muted-foreground hover:text-foreground hover:bg-secondary/50'
                }`
              }
            >
              Teams
            </NavLink>
            <NavLink
              to="/dashboard/projects"
              className={({ isActive }) =>
                `block px-3 py-2 rounded-md text-sm font-medium ${isActive
                  ? 'bg-secondary text-secondary-foreground'
                  : 'text-muted-foreground hover:text-foreground hover:bg-secondary/50'
                }`
              }
            >
              Projects
            </NavLink>
          </div>
        </div>
      </nav>

      {/* Main Content Area */}
      <main className="flex-1">
        <div className="max-w-7xl mx-auto py-6 px-4 sm:px-6 lg:px-8">
          {/* This will render child routes */}
          <Outlet />
        </div>
      </main>
    </div>
  );
}
