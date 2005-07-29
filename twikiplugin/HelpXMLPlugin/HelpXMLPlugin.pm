# Plugin for TWiki Collaboration Platform, http://TWiki.org/
#
# Copyright (C) 2000-2003 Andrea Sterbini, a.sterbini@flashnet.it
# Copyright (C) 2001-2004 Peter Thoeny, peter@thoeny.com
#
# This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU General Public License
# as published by the Free Software Foundation; either version 2
# of the License, or (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details, published at 
# http://www.gnu.org/copyleft/gpl.html
#
# =========================
#
# This is an empty TWiki plugin. Use it as a template
# for your own plugins; see TWiki.TWikiPlugins for details.
#
# Each plugin is a package that may contain these functions:        VERSION:
#
#   earlyInitPlugin         ( )                                     1.020
#   initPlugin              ( $topic, $web, $user, $installWeb )    1.000
#   initializeUserHandler   ( $loginName, $url, $pathInfo )         1.010
#   registrationHandler     ( $web, $wikiName, $loginName )         1.010
#   beforeCommonTagsHandler ( $text, $topic, $web )                 1.024
#   commonTagsHandler       ( $text, $topic, $web )                 1.000
#   afterCommonTagsHandler  ( $text, $topic, $web )                 1.024
#   startRenderingHandler   ( $text, $web )                         1.000
#   outsidePREHandler       ( $text )                               1.000
#   insidePREHandler        ( $text )                               1.000
#   endRenderingHandler     ( $text )                               1.000
#   beforeEditHandler       ( $text, $topic, $web )                 1.010
#   afterEditHandler        ( $text, $topic, $web )                 1.010
#   beforeSaveHandler       ( $text, $topic, $web )                 1.010
#   afterSaveHandler        ( $text, $topic, $web, $errors )        1.020
#   writeHeaderHandler      ( $query )                              1.010  Use only in one Plugin
#   redirectCgiQueryHandler ( $query, $url )                        1.010  Use only in one Plugin
#   getSessionValueHandler  ( $key )                                1.010  Use only in one Plugin
#   setSessionValueHandler  ( $key, $value )                        1.010  Use only in one Plugin
#
# initPlugin is required, all other are optional. 
# For increased performance, all handlers except initPlugin are
# disabled. To enable a handler remove the leading DISABLE_ from
# the function name. Remove disabled handlers you do not need.
#
# NOTE: To interact with TWiki use the official TWiki functions 
# in the TWiki::Func module. Do not reference any functions or
# variables elsewhere in TWiki!!


# =========================
package TWiki::Plugins::HelpXMLPlugin;    # change the package name and $pluginName!!!

# =========================
use vars qw(
        $web $topic $user $installWeb $VERSION $pluginName
        $debug $exampleCfgVar
    );

$VERSION = '1.00';
$pluginName = 'HelpXMLPlugin';  # Name of this Plugin

# =========================
sub initPlugin
{
    ( $topic, $web, $user, $installWeb ) = @_;

    # check for Plugins.pm versions
    if( $TWiki::Plugins::VERSION < 1.00 ) {
        TWiki::Func::writeWarning( "Version mismatch between $pluginName and Plugins.pm" );
        return 0;
    }

    # Get plugin debug flag
    $debug = TWiki::Func::getPluginPreferencesFlag( "DEBUG" );

    # Plugin correctly initialized
    TWiki::Func::writeDebug( "- TWiki::Plugins::${pluginName}::initPlugin( $web.$topic ) is OK" ) if $debug;
    return 1;
}

# =========================
sub commonTagsHandler
{
### my ( $text, $topic, $web ) = @_;   # do not uncomment, use $_[0], $_[1]... instead

    TWiki::Func::writeDebug( "- ${pluginName}::commonTagsHandler( $_[2].$_[1] )" ) if $debug;

    # This is the place to define customized tags and variables
    # Called by TWiki::handleCommonTags, after %INCLUDE:"..."%
    $_[0] =~ s/%HELPXML\{(.+)\}%/&handleHelpXML($1,$_[2])/ge;
    
}

# =========================
sub handleHelpXML  # clone of TWiki::handleIncludeIndex
{
	my ($theAttributes, $theWeb) = @_;
	my $file = &TWiki::extractNameValuePair($theAttributes);
	my $url = &TWiki::extractNameValuePair($theAttributes, "url");
	
    $file =~ s/$TWiki::securityFilter//go;    # zap anything suspicious
    $file =~ s/passwd//goi;    # filter out passwd filename

    if( $TWiki::doSecureInclude ) {
        # Filter out ".." from filename, this is to
        # prevent includes of "../../file"
        $file =~ s/\.+/\./g;
    }
	
	if ( $file =~ m|^(.+)[./](.*)$| ) {
		$theWeb = $1;
		$theTopic = $2;
    } else	{
		$theTopic = $file
    }
	
	my $text = "";
    my $meta = "";

    # set include web/filenames and current web/filenames
    {
        ( $meta, $text ) = &TWiki::Store::readTopic( $theWeb, $theTopic );
        # remove everything before %STARTINCLUDE% and after %STOPINCLUDE%
        $text =~ s/.*?%STARTINCLUDE%//os;
        $text =~ s/%STOPINCLUDE%.*//os;
    }
	
	$text = &TWiki::Func::expandCommonVariables($text, $theTopic);
	my @lines = split /^/m, $text;
    my $result = "<pre>&lt;?xml version='1.0' encoding='UTF-8'?>\n&lt;?NLS TYPE='org.eclipse.help.toc'?>\n";
	
	my $header1 = 0;
	my $header2 = 0;
	my $header3 = 0;
	my $header4 = 0;
	
    foreach $line (@lines)
    {
		if ($line =~ /^----*\+/)	{
			if ($line =~ /^----*\+\s+/) #Header 1
			{
				if ($header1)
				{
					return "HelpXMLPlugin Error: Page $theWeb/$theTopic should not have more than 1 header1";
				}
				$line =~ s/^----*\+\s+//mg;
				$line =~ s/\n$//;
				$header1 = 1;
				$result .= "&lt;toc label='$line' topic='$url'>\n";
			}
			elsif ($line =~ /^----*\+\+\s+/) #Header2
			{
				if ($header2)
				{
					if ($header3)
					{
						$result .= "        &lt;/topic>\n";
						$header3 = 0;
					}
					$result .= "    &lt;/topic>\n";
					$header2 = 0;
				}
				$line =~ s/^----*\+\+\s+//mg;
				$line =~ s/\n$//;
				$header2 = 1;
				$result .= "    &lt;topic label='$line' href='$url#";
				$line =~ s/\s/_/mg;
				$line =~ s/[-\\\/]/_/mg;
				$line =~ s/\W//mg;
				$line =~ s/_$//;
				$line =~ s/[_]+/_/mg;
				$line = substr($line, 0, 32);
				$result .= "$line'>\n";
			}
			elsif ($line =~ /^----*\+\+\+\s+/) #Header3
			{
				if ($header3)
				{
					$result .= "        &lt;/topic>\n";
					$header3 = 0;
				}
				$line =~ s/^----*\+\+\+\s+//mg;
				$line =~ s/\n$//;
				$header3 = 1;
				$result .= "        &lt;topic label='$line' href='$url#";
				$line =~ s/\s/_/mg;
				$line =~ s/[-\\\/]/_/mg;
				$line =~ s/\W//mg;
				$line =~ s/_$//;
				$line =~ s/_+/_/;
				$line = substr($line, 0, 32);
				$result .= "$line'>\n";
			}
			elsif ($line =~ /^----*\+\+\+\+\s+/) #Header4
			{
				$line =~ s/^----*\+\+\+\+\s+//mg;
				$line =~ s/\n$//;
				$header4 = 1;
				$result .= "            &lt;topic label='$line' href='$url#";
				$line =~ s/\s/_/mg;
				$line =~ s/[-\\\/]/_/mg;
				$line =~ s/\W//mg;
				$line =~ s/_$//;
				$line =~ s/_+/_/;
				$line = substr($line, 0, 32);
				$result .= "$line' />\n";			
			}
		}
	}

	if ($header3)
	{
		$result .= "        &lt;/topic>\n";
	}
	if ($header2)
	{
		$result .= "    &lt;/topic>\n";
	}
	
	$result .= "&lt;/toc>\n</pre>";
    chomp $result;
	return $result;
}

1;
