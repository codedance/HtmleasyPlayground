#!/bin/sh

DATE=`date +%Y-%m-%d`

find . -print \
	| grep -v "git" \
	| grep -v "package\.sh" \
	| zip HtmlPlayground-${DATE}.zip -@
