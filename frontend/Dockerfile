FROM node:12-alpine
WORKDIR /app
RUN pwd
RUN ls -l
COPY package.json package-lock.json ./
RUN npm install
COPY . .
EXPOSE 3000
CMD ["npm", "start"]
