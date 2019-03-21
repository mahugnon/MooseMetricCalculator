Class {
	#name : #MMCFAMIXClassGroupAdapter,
	#superclass : #MMCAbstractAdapter,
	#category : #'Moose-Metric-Calculator-Adapter'
}

{ #category : #utilities }
MMCFAMIXClassGroupAdapter class >> adaptedClass [
	^ FAMIXClassGroup
]

{ #category : #visiting }
MMCFAMIXClassGroupAdapter >> acceptAssociationQuery [
	^ false
]

{ #category : #visiting }
MMCFAMIXClassGroupAdapter >> acceptNavigationQuery [ 
	^ false
]

{ #category : #visiting }
MMCFAMIXClassGroupAdapter >> acceptPropertyQuery [
	^ true
]
