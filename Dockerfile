FROM alpine:3
# Donwload tabler zip files from https://github.com/tabler/tabler
COPY tabler-dev /tabler-files
WORKDIR /tabler-files
RUN apk add --update --no-cache alpine-sdk ruby nodejs npm git
RUN npm install
RUN gem install bundler
RUN bundle install
ENTRYPOINT [ "npm","run","start" ]
EXPOSE 3000