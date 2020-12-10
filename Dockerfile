FROM node:current-alpine3.12
COPY tabler-dev-2020-12-10 /tabler-dev

WORKDIR /tabler-dev
# TODO: fix up the below step
# RUN npm install
# RUN apk add --no-cache ruby
# RUN gem install bundler
# RUN bundle installer