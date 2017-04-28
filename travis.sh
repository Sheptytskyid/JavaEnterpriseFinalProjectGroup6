#!/bin/bash

if [ $(curl -X GET -H "Accept: application/vnd.github.black-cat-preview+json" https://api.github.com/repos/Sheptytskyid/Startup_Platform/pulls/$TRAVIS_PULL_REQUEST/reviews | jq '.[] | select(.user.login == "zozich") | .state == "APPROVED"') == true ];
then state="success" desc="Zozich has approved this PR!";
else state="failure" desc="Zozich has not approved this PR!";
fi
curl -H "Authorization: token $GITHUB_TOKEN" -d "{
  \"state\": \"$state\",
  \"description\": \"$desc\",
  \"context\": \"Zozich appoval\"
}" https://api.github.com/repos/Sheptytskyid/Startup_Platform/statuses/$TRAVIS_PULL_REQUEST_SHA
echo TRAVIS_COMMIT = $TRAVIS_COMMIT
echo TRAVIS_PULL_REQUEST_SHA = $TRAVIS_PULL_REQUEST_SHA
echo TRAVIS_PULL_REQUEST = $TRAVIS_PULL_REQUEST
echo TRAVIS_PULL_REQUEST_BRANCH = $TRAVIS_PULL_REQUEST_BRANCH
echo TRAVIS_PULL_REQUEST_SLUG = $TRAVIS_PULL_REQUEST_SLUG