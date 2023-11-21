use strict;
use warnings;

# Requires the "CAM::PDF" library for PDF processing
use CAM::PDF;
use File::Basename;

sub extractTextFromPdf {
    my ($pdfFile) = @_;
    my $pdf = CAM::PDF->new($pdfFile);
    my $text = '';
    for my $page (1 .. $pdf->numPages) {
        $text .= $pdf->getPageText($page);
    }
    return $text;
}

sub extractTextFromDocx {
    my ($docxFile) = @_;
    # Use an external tool or library to extract text from a DOCX file
    # For example, you can use "pandoc" with system('pandoc ...') or another tool
}

sub extractTextFromTxt {
    my ($txtFile) = @_;
    my $text = '';
    open my $fh, '<', $txtFile or die "Cannot open $txtFile: $!";
    while (my $line = <$fh>) {
        $text .= $line;
    }
    close $fh;
    return $text;
}

sub summarizeText {
    my ($text) = @_;
    # Implement text summarization logic here
    # You can use external libraries or custom methods
}

print "Enter the path of the file (PDF, DOCX, or TXT): ";
my $inputFile = <STDIN>;
chomp $inputFile;
my $fileExtension = lc(pathfile($inputFile, 1));

my $text = '';
if ($fileExtension eq 'pdf') {
    $text = extractTextFromPdf($inputFile);
} elsif ($fileExtension eq 'docx') {
    $text = extractTextFromDocx($inputFile);
} elsif ($fileExtension eq 'txt') {
    $text = extractTextFromTxt($inputFile);
} else {
    print "Unsupported file format\n";
}

if ($text) {
    my $summary = summarizeText($text);
    print "Summary:\n";
    print $summary . "\n";
}
