# PDF'ER

## Description

Have a web page but need a PDF? Use pdf'er to pdf'it!
pdf'er is a web service wrapper to [wkhtmltopdf](http://code.google.com/p/wkhtmltopdf/)

## Parameters

- url - webpage URL
- html_inner - innerHTML, if present URL param is ignored
- html_head - (optional) defaults to &lt;html&gt;&lt;head&gt;&lt;/head&gt;&lt;body&gt;
- html_foot - (optional) defaults to &lt;/body&gt;&lt;/html&gt;
- file - (optional) output filename, defaults to "output"
- options - (optional) string of options supported by wkhtmltopdf
- format - pdf|png defaults to pdf

## Usage
    ~/fit?url=www.google.com

## Dependecies

wkhtmltopdf

```
wget http://wkhtmltopdf.googlecode.com/files/wkhtmltopdf-0.9.9-static-amd64.tar.bz2
tar -xf wkhtmltopdf-0.9.9-static-amd64.tar.bz2
mv wkhtmltopdf-amd64 /bin/wkhtmltopdf

wget http://wkhtmltopdf.googlecode.com/files/wkhtmltoimage-0.10.0_rc2-static-i386.tar.bz2
tar -xf wkhtmltoimage-0.10.0_rc2-static-i386.tar.bz2 
mv wkhtmltoimage-i386 /bin/wkhtmltoimage
```