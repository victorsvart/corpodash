import { defineConfig } from 'orval';
export default defineConfig({
  api: {
    output: {
      mode: 'tags-split',
      target: 'app/gen/api.ts',
      schemas: 'app/gen/models',
      client: 'fetch',
      baseUrl: 'http://localhost:8080',
      mock: false,
    },

    input: {
      target: 'http://localhost:8080/v3/api-docs',
    },
  },
});
