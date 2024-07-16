import xml.etree.ElementTree as ET
import sys
import os

def jacoco_to_lcov(jacoco_xml_path, lcov_output_path):
    tree = ET.parse(jacoco_xml_path)
    root = tree.getroot()

    # Create the directory for the LCOV output file if it doesn't exist
    os.makedirs(os.path.dirname(lcov_output_path), exist_ok=True)

    with open(lcov_output_path, 'w') as lcov_file:
        lcov_file.write('TN:\n')
        for package in root.findall('package'):
            package_name = package.get('name')
            for sourcefile in package.findall('sourcefile'):
                sourcefile_name = sourcefile.get('name')
                lcov_file.write(f'SF:{package_name}/{sourcefile_name}\n')
                for line in sourcefile.findall('line'):
                    line_number = line.get('nr')
                    line_hits = line.get('ci')
                    lcov_file.write(f'DA:{line_number},{line_hits}\n')
                lcov_file.write('end_of_record\n')

if __name__ == '__main__':
    jacoco_xml_path = sys.argv[1]
    lcov_output_path = sys.argv[2]
    jacoco_to_lcov(jacoco_xml_path, lcov_output_path)
