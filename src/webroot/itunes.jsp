<%@ taglib uri="http://marklogic.com/jsp/taglib" prefix="xq" %>
<html>
<head>
<title>iTunes Test</title>
</head>
<body style="font-family:Arial,Helvetica,san-serif">
<div align="center"><h1>Ron's iTunes Tracks</h1></div>
<table style="background-color:#bbffff" width="90%" border="1" cellspacing="0" cellpadding="4" align="center">
<tr style="color:white;background-color:black"><th>Track</th><th>Artist</th><th>Album</th><th>Genre</th></tr>
<xq:setDataSource host="ronsoft.net" port="8003"/>
<xq:query>
	for $track in doc("itunes.xml")/plist/dict/dict[preceding-sibling::key = "Tracks"]/dict
		let $name := $track/string[preceding-sibling::key = "Name"][1],
		$artist := $track/string[preceding-sibling::key = "Artist"][1],
		$album := $track/string[preceding-sibling::key = "Album"][1],
		$genre := $track/string[preceding-sibling::key = "Genre"][1]

		where $artist and $genre
		order by $artist, $album

		return <tr>
			<td><b>{$name}</b></td>
			<td>{$artist}</td>
			<td>{$album}</td>
			<td>{$genre}</td>
		</tr>
</xq:query>
</table>
</body>
</html>