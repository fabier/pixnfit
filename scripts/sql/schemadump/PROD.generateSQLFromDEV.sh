#!/bin/bash
now=$(date +"%Y-%m-%d")

prodSchemaFile="PROD.SCHEMA.$now.sql"
devSchemaFile="DEV.SCHEMA.$now.sql"

echo
echo "CREATING DEV SCHEMA FILE : $devSchemaFile ..."
echo "pg_dump -h localhost -U dev -s -f $devSchemaFile pixnfit"
pg_dump -h localhost -U dev -s -f "$devSchemaFile" pixnfit

echo
echo "CREATING PROD SCHEMA FILE : $prodSchemaFile ..."
echo "pg_dump -h fabier.fr -U dev -s -f $prodSchemaFile pixnfit"
echo "Please type in PROD password"
pg_dump -h fabier.fr -U dev -s -f "$prodSchemaFile" pixnfit
echo

echo "##############################"
echo "GENERATING DIFF FOR DEV SERVER"
echo "##############################"
echo "java -jar apgdiff-2.4.jar --ignore-start-with $prodSchemaFile $devSchemaFile"
echo
echo
echo
echo

java -jar apgdiff-2.4.jar --ignore-start-with "$prodSchemaFile" "$devSchemaFile"

echo
echo
echo
echo
echo "##############################"
echo "REMOVING TEMP FILES ..."
echo "rm $prodSchemaFile"
rm "$prodSchemaFile"
echo "rm $devSchemaFile"
rm "$devSchemaFile"
echo
echo "OK FINISHED"
echo
