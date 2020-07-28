set -e

LATEST_VERSION_TOKENS_LIB_NPM=`npm show @useblu/tokens@beta version`
echo "\033[1;31m >>>> Current version is '$LATEST_VERSION_TOKENS_LIB_NPM'. \033[0m"

echo '\033[1;31m >>>> Getting new version and saving on version.properties file. \033[0m'
sed -i-e "/VERSION_NAME=/ s/=.*/=$LATEST_VERSION_TOKENS_LIB_NPM/g" "ocean-ds-components/version.properties"

echo '\033[1;31m >>>> Deleting unnecessary temp file. \033[0m'
rm -f "ocean-ds-components/version.properties-e"

echo '\033[1;31m >>>> What is the current DIR?\033[0m'
pwd

echo '\033[1;31m >>>> Downloading tokens into npm lib…\033[0m'
npm install @useblu/tokens@beta --save-dev

echo '\033[1;31m >>>> Copying resources XML file \033[0m'
cp node_modules/@useblu/tokens/dist/android/tokens.xml ocean-ds-tokens/src/main/res/values/tokens.xml

echo '\033[1;31m >>>> Deleting all old font files \033[0m'
rm -rf ocean-ds-tokens/src/main/res/font/*.ttf

echo '\033[1;31m >>>> Copying all font files \033[0m'
cp node_modules/@useblu/tokens/dist/assets/fonts/*/*.ttf ocean-ds-tokens/src/main/res/font

cd ocean-ds-tokens/src/main/res/font

echo '\033[1;31m >>>> Renaming all font files. \033[0m'
for f in *.ttf
do
	mv -f $f $(echo $f |
	sed -e 's/Avenir/font_family_highlight/g' |
	sed -e 's/NunitoSans/font_family_base/g' |
	sed -e 's/-/_/g' |
	sed -e 's/Roman/Regular/g' |
	sed -e 's/SemiBold/Medium/g' |
	sed -e 's/Heavy/Bold/g' |
  sed -e 's/Black/Extrabold/g' |
	tr '[:upper:]' '[:lower:]')
done

echo '\033[1;31m >>>> What is the current DIR?\033[0m'
pwd

cd ../../../../..

echo '\033[1;31m >>>> What is the current DIR?\033[0m'
pwd

echo '\033[1;31m >>>> Show git status. \033[0m'
git status

echo '\033[1;31m >>>> Running gradlew build \033[0m'
./gradlew clean assembleRelease publish --stacktrace --info --debug

echo '\033[1;31m >>>> Setting upstream branch: '${CIRCLE_BRANCH}'. \033[0m'
git push --set-upstream origin ${CIRCLE_BRANCH}

echo '\033[1;31m >>>> Setting user & email. \033[0m'
git config user.email "luis.fernandez@mobiplus.com.br"
git config user.name "Luis Fernandez"

echo '\033[1;31m >>>> Add and Commit new classes. \033[0m'
git add . && git commit -am "Generating classes based on @useblu/tokens npm lib version: $LATEST_VERSION_TOKENS_LIB_NPM" && git push

echo '\033[1;31m >>>> Show git status after commands.  \033[0m'
git status