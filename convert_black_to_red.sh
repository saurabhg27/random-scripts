# Convert an image/pdf with black text and images into red using ImageMagick
# Helps if you want to save black printer ink
# https://superuser.com/a/1548631/1130873

echo "start"
for filename in *.pdf; do
	outfilename="${filename%.pdf} output.pdf"
	echo "---- start $filename --> $outfilename ----"
	magick.exe -density 300 "$filename" -fuzz 50% -fill red -opaque black "$outfilename"
	echo "---- done $outfilename ----"
done

echo "finished"
