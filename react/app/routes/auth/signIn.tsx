import { data, Form, Link, redirect, useActionData } from "react-router";
import type { Route } from "./+types/signIn";
import { Card, CardAction, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "~/components/ui/card";
import { Label } from "@radix-ui/react-label";
import { Button } from "~/components/ui/button";
import { Input } from "~/components/ui/input";
import { me, signIn } from "~/gen/user-controller/user-controller";
import type { ErrResponse } from "~/lib/errorResponse";
import { AlertCircleIcon } from "lucide-react";
import { Alert, AlertDescription, AlertTitle } from "~/components/ui/alert";

export async function clientLoader() {
  const response = await me({ credentials: 'include' })
  if (response.status == 200) {
    return redirect("/");
  }

  return data({});
}

export async function clientAction({ request }: Route.ClientActionArgs) {
  const formData = Object.fromEntries(await request.formData());
  const response = await signIn({ ...formData }, { credentials: 'include' });
  if (response.status != 200) {
    return response.data;
  }

  return redirect('/');
}

export default function SignIn() {
  const actionData = useActionData() as { error?: ErrResponse };

  return (
    <Form method="post">
      <div className="flex min-h-screen items-center justify-center">
        <Card className="w-full max-w-sm">
          <CardHeader>
            <CardTitle>Login to your account</CardTitle>
            <CardDescription>
              Enter your email below to login to your account
            </CardDescription>
            <CardAction>
              <Link to="/auth/signUp">
                <Button variant="link">Sign Up</Button>
              </Link>
            </CardAction>
          </CardHeader>

          <CardContent>
            <div className="flex flex-col gap-6">
              <div className="grid gap-2">
                <Label htmlFor="email">Email</Label>
                <Input
                  id="email"
                  type="email"
                  name="email"
                  placeholder="m@example.com"
                  required
                />
              </div>
              <div className="grid gap-2">
                <div className="flex items-center">
                  <Label htmlFor="password">Password</Label>
                  <a
                    href="#"
                    className="ml-auto inline-block text-sm underline-offset-4 hover:underline"
                  >
                    Forgot your password?
                  </a>
                </div>
                <Input id="password" type="password" name="password" required />
              </div>
            </div>

            {actionData?.error && (
              <Alert variant="destructive" className="mt-6">
                <AlertCircleIcon className="h-5 w-5" />
                <AlertTitle>Login failed</AlertTitle>
                <AlertDescription>
                  <p>{actionData.error.message}</p>
                </AlertDescription>
              </Alert>
            )}
          </CardContent>

          <CardFooter className="flex-col gap-2">
            <Button type="submit" className="w-full">
              Login
            </Button>
            <Button variant="outline" className="w-full">
              Login with Google
            </Button>
          </CardFooter>
        </Card>
      </div>
    </Form>
  );
}
