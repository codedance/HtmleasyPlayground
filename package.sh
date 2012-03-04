#!/bin/sh

find . -name ".DS_Store" -depth -exec rm {} \;

DATE=`date +%Y-%m-%d`

cd ..
find HtmleasyPlayground -print \
	| grep -v "git" \
	| grep -v "package\.sh" \
	| zip HtmleasyPlayground/HtmleasyPlayground-${DATE}.zip -@
